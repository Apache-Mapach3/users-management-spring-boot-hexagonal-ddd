package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateUserUseCase;
import com.jcaa.usersmanagement.application.port.out.GetUserByEmailPort;
import com.jcaa.usersmanagement.application.port.out.GetUserByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateUserPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateUserCommand;
import com.jcaa.usersmanagement.application.service.mapper.UserApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.UserAlreadyExistsException;
import com.jcaa.usersmanagement.domain.exception.UserNotFoundException;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.UserEmail;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Set;

@Service
public class UpdateUserService implements UpdateUserUseCase {

  private final UpdateUserPort updateUserPort;
  private final GetUserByIdPort getUserByIdPort;
  private final GetUserByEmailPort getUserByEmailPort;
  private final EmailNotificationService emailNotificationService;
  private final Validator validator;

  public UpdateUserService(
          final UpdateUserPort updateUserPort,
          final GetUserByIdPort getUserByIdPort,
          final GetUserByEmailPort getUserByEmailPort,
          final EmailNotificationService emailNotificationService,
          final Validator validator) {
    this.updateUserPort = Objects.requireNonNull(updateUserPort, "El updateUserPort no puede ser nulo");
    this.getUserByIdPort = Objects.requireNonNull(getUserByIdPort, "El getUserByIdPort no puede ser nulo");
    this.getUserByEmailPort = Objects.requireNonNull(getUserByEmailPort, "El getUserByEmailPort no puede ser nulo");
    this.emailNotificationService = Objects.requireNonNull(emailNotificationService, "El emailNotificationService no puede ser nulo");
    this.validator = Objects.requireNonNull(validator, "El validator no puede ser nulo");
  }

  @Override
  public UserModel execute(final UpdateUserCommand command) {
    validateCommand(command);

    final UserId userId = new UserId(command.id());
    final UserModel current = findExistingUserOrFail(userId);
    final UserEmail newEmail = new UserEmail(command.email());

    ensureEmailIsNotTakenByAnotherUser(newEmail, userId);

    final UserModel userToUpdate =
            UserApplicationMapper.fromUpdateCommandToModel(command, current.getPassword());
    final UserModel updatedUser = updateUserPort.update(userToUpdate);

    emailNotificationService.notifyUserUpdated(updatedUser);

    return updatedUser;
  }

  private void validateCommand(final UpdateUserCommand command) {
    final Set<ConstraintViolation<UpdateUserCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private UserModel findExistingUserOrFail(final UserId userId) {
    return getUserByIdPort
            .getById(userId)
            .orElseThrow(() -> UserNotFoundException.becauseIdWasNotFound(userId.value()));
  }

  private void ensureEmailIsNotTakenByAnotherUser(final UserEmail newEmail, final UserId ownerId) {
    getUserByEmailPort
            .getByEmail(newEmail)
            .ifPresent(
                    found -> {
                      if (!found.getId().equals(ownerId)) {
                        throw UserAlreadyExistsException.becauseEmailAlreadyExists(newEmail.value());
                      }
                    });
  }
}
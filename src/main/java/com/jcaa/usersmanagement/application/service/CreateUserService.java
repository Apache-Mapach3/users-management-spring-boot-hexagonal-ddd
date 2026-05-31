package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateUserUseCase;
import com.jcaa.usersmanagement.application.port.out.GetUserByEmailPort;
import com.jcaa.usersmanagement.application.port.out.SaveUserPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateUserCommand;
import com.jcaa.usersmanagement.application.service.mapper.UserApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.UserAlreadyExistsException;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.UserEmail;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
public class CreateUserService implements CreateUserUseCase {

  private final SaveUserPort saveUserPort;
  private final GetUserByEmailPort getUserByEmailPort;
  private final EmailNotificationService emailNotificationService;
  private final Validator validator;

  public CreateUserService(
          final SaveUserPort saveUserPort,
          final GetUserByEmailPort getUserByEmailPort,
          final EmailNotificationService emailNotificationService,
          final Validator validator) {
    this.saveUserPort = Objects.requireNonNull(saveUserPort, "El saveUserPort no puede ser nulo");
    this.getUserByEmailPort = Objects.requireNonNull(getUserByEmailPort, "El getUserByEmailPort no puede ser nulo");
    this.emailNotificationService = Objects.requireNonNull(emailNotificationService, "El emailNotificationService no puede ser nulo");
    this.validator = Objects.requireNonNull(validator, "El validator no puede ser nulo");
  }

  @Override
  public UserModel execute(final CreateUserCommand command) {
    validateCommand(command);

    final UserEmail email = new UserEmail(command.email());
    ensureEmailIsNotTaken(email);

    final UserModel userToSave = UserApplicationMapper.fromCreateCommandToModel(command);
    final UserModel savedUser = saveUserPort.save(userToSave);

    emailNotificationService.notifyUserCreated(savedUser, command.password());

    return savedUser;
  }

  private void validateCommand(final CreateUserCommand command) {
    final Set<ConstraintViolation<CreateUserCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureEmailIsNotTaken(final UserEmail email) {
    getUserByEmailPort
            .getByEmail(email)
            .ifPresent(
                    ignored -> {
                      throw UserAlreadyExistsException.becauseEmailAlreadyExists(email.value());
                    });
  }
}
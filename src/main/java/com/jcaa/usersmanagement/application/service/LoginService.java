package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.LoginUseCase;
import com.jcaa.usersmanagement.application.port.out.GetUserByEmailPort;
import com.jcaa.usersmanagement.application.service.dto.command.LoginCommand;
import com.jcaa.usersmanagement.domain.enums.UserStatus;
import com.jcaa.usersmanagement.domain.exception.InvalidCredentialsException;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.UserEmail;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Objects;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginUseCase {

  private final GetUserByEmailPort getUserByEmailPort;
  private final Validator validator;

  public LoginService(
          final GetUserByEmailPort getUserByEmailPort,
          final Validator validator) {
    this.getUserByEmailPort = Objects.requireNonNull(getUserByEmailPort, "El getUserByEmailPort no puede ser nulo");
    this.validator = Objects.requireNonNull(validator, "El validator no puede ser nulo");
  }

  @Override
  public UserModel execute(final LoginCommand command) {
    validateCommand(command);

    final UserEmail email = new UserEmail(command.email());
    final UserModel user = findUserOrFailWithInvalidCredentials(email);

    verifyPasswordOrFail(command.password(), user);
    ensureUserIsActiveOrFail(user);

    return user;
  }

  private void validateCommand(final LoginCommand command) {
    final Set<ConstraintViolation<LoginCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private UserModel findUserOrFailWithInvalidCredentials(final UserEmail email) {
    return getUserByEmailPort
            .getByEmail(email)
            .orElseThrow(InvalidCredentialsException::becauseCredentialsAreInvalid);
  }

  private static void verifyPasswordOrFail(final String plainPassword, final UserModel user) {
    if (!user.getPassword().verifyPlain(plainPassword)) {
      throw InvalidCredentialsException.becauseCredentialsAreInvalid();
    }
  }

  private static void ensureUserIsActiveOrFail(final UserModel user) {
    if (user.getStatus() != UserStatus.ACTIVE) {
      throw InvalidCredentialsException.becauseUserIsNotActive();
    }
  }
}
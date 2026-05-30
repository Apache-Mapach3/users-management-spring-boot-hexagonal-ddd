package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetUserByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetUserByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetUserByIdQuery;
import com.jcaa.usersmanagement.application.service.mapper.UserApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.UserNotFoundException;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Objects;
import java.util.Set;

public class GetUserByIdService implements GetUserByIdUseCase {

  private final GetUserByIdPort getUserByIdPort;
  private final Validator validator;
  public GetUserByIdService(
          final GetUserByIdPort getUserByIdPort,
          final Validator validator) {
    this.getUserByIdPort = Objects.requireNonNull(getUserByIdPort, "El getUserByIdPort no puede ser nulo");
    this.validator = Objects.requireNonNull(validator, "El validator no puede ser nulo");
  }

  @Override
  public UserModel execute(final GetUserByIdQuery query) {
    validateQuery(query);

    final UserId userId = UserApplicationMapper.fromGetUserByIdQueryToUserId(query);
    return getUserByIdPort
            .getById(userId)
            .orElseThrow(() -> UserNotFoundException.becauseIdWasNotFound(userId.value()));
  }

  private void validateQuery(final GetUserByIdQuery query) {
    final Set<ConstraintViolation<GetUserByIdQuery>> violations = validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
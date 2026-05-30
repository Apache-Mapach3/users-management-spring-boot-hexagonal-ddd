package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllUsersUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllUsersPort;
import com.jcaa.usersmanagement.domain.model.UserModel;

import java.util.List;
import java.util.Objects;

public class GetAllUsersService implements GetAllUsersUseCase {

  private final GetAllUsersPort getAllUsersPort;

  public GetAllUsersService(final GetAllUsersPort getAllUsersPort) {
    this.getAllUsersPort = Objects.requireNonNull(getAllUsersPort, "El getAllUsersPort no puede ser nulo");
  }

  @Override
  public List<UserModel> execute() {
    return getAllUsersPort.getAll();
  }
}
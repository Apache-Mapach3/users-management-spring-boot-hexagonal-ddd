package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllCensosUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllCensosPort;
import com.jcaa.usersmanagement.domain.model.CensoModel;

import java.util.List;
import java.util.Objects;


public class GetAllCensosService implements GetAllCensosUseCase {

    private final GetAllCensosPort getAllCensosPort;

    public GetAllCensosService(final GetAllCensosPort getAllCensosPort) {
        this.getAllCensosPort = Objects.requireNonNull(getAllCensosPort, "El getAllCensosPort no puede ser nulo");
    }

    @Override
    public List<CensoModel> execute() {
        return getAllCensosPort.findAll();
    }
}
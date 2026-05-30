package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetCensoByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetCensoByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetCensoByIdQuery;
import com.jcaa.usersmanagement.domain.exception.CensoNotFoundException;
import com.jcaa.usersmanagement.domain.model.CensoModel;

import java.util.Objects;


public class GetCensoByIdService implements GetCensoByIdUseCase {

    private final GetCensoByIdPort getCensoByIdPort;


    public GetCensoByIdService(final GetCensoByIdPort getCensoByIdPort) {
        this.getCensoByIdPort = Objects.requireNonNull(getCensoByIdPort, "El getCensoByIdPort no puede ser nulo");
    }

    @Override
    public CensoModel execute(GetCensoByIdQuery query) {
        return getCensoByIdPort.findById(query.censoId())
                .orElseThrow(() -> CensoNotFoundException.becauseIdWasNotFound(query.censoId()));
    }
}
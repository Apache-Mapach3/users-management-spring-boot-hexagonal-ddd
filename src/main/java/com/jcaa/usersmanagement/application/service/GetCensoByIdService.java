package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetCensoByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetCensoByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetCensoByIdQuery;
import com.jcaa.usersmanagement.domain.exception.CensoNotFoundException;
import com.jcaa.usersmanagement.domain.model.CensoModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetCensoByIdService implements GetCensoByIdUseCase {

    private final GetCensoByIdPort getCensoByIdPort;

    @Override
    public CensoModel execute(GetCensoByIdQuery query) {
        return getCensoByIdPort.execute(query.censoId())
                .orElseThrow(() -> CensoNotFoundException.becauseIdWasNotFound(query.censoId()));
    }
}
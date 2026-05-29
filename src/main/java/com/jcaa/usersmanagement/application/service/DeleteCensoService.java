package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteCensoUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteCensoPort;
import com.jcaa.usersmanagement.application.port.out.GetCensoByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteCensoCommand;
import com.jcaa.usersmanagement.domain.exception.CensoNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteCensoService implements DeleteCensoUseCase {

    private final GetCensoByIdPort getCensoByIdPort;
    private final DeleteCensoPort deleteCensoPort;

    @Override
    public void execute(DeleteCensoCommand command) {
        getCensoByIdPort.findById(command.censoId())
                .orElseThrow(() -> CensoNotFoundException.becauseIdWasNotFound(command.censoId()));
        deleteCensoPort.delete(command.censoId());
    }
}
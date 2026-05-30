package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteCensoUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteCensoPort;
import com.jcaa.usersmanagement.application.port.out.GetCensoByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteCensoCommand;
import com.jcaa.usersmanagement.domain.exception.CensoNotFoundException;

import java.util.Objects;

public class DeleteCensoService implements DeleteCensoUseCase {

    private final GetCensoByIdPort getCensoByIdPort;
    private final DeleteCensoPort deleteCensoPort;

    public DeleteCensoService(
            final GetCensoByIdPort getCensoByIdPort,
            final DeleteCensoPort deleteCensoPort) {
        this.getCensoByIdPort = Objects.requireNonNull(getCensoByIdPort, "El getCensoByIdPort no puede ser nulo");
        this.deleteCensoPort = Objects.requireNonNull(deleteCensoPort, "El deleteCensoPort no puede ser nulo");
    }

    @Override
    public void execute(DeleteCensoCommand command) {
        getCensoByIdPort.findById(command.censoId())
                .orElseThrow(() -> CensoNotFoundException.becauseIdWasNotFound(command.censoId()));
        deleteCensoPort.delete(command.censoId());
    }
}
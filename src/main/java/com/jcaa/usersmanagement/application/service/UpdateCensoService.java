package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateCensoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetCensoByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateCensoPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateCensoCommand;
import com.jcaa.usersmanagement.domain.exception.CensoNotFoundException;
import com.jcaa.usersmanagement.domain.model.CensoModel;

import java.util.Objects;

public class UpdateCensoService implements UpdateCensoUseCase {

    private final GetCensoByIdPort getCensoByIdPort;
    private final UpdateCensoPort updateCensoPort;

    public UpdateCensoService(
            final GetCensoByIdPort getCensoByIdPort,
            final UpdateCensoPort updateCensoPort) {
        this.getCensoByIdPort = Objects.requireNonNull(getCensoByIdPort, "El getCensoByIdPort no puede ser nulo");
        this.updateCensoPort = Objects.requireNonNull(updateCensoPort, "El updateCensoPort no puede ser nulo");
    }

    @Override
    public CensoModel execute(UpdateCensoCommand command) {
        CensoModel existingCenso = getCensoByIdPort.findById(command.censoId())
                .orElseThrow(() -> CensoNotFoundException.becauseIdWasNotFound(command.censoId()));

        CensoModel updatedCenso = CensoModel.builder()
                .censoId(existingCenso.getCensoId())
                .fecha(existingCenso.getFecha())
                .nombre(command.nombre())
                .pais(command.pais())
                .departamento(command.departamento())
                .ciudad(command.ciudad())
                .casa(command.casa())
                .numHombres(command.numHombres())
                .numMujeres(command.numMujeres())
                .numAncianosHombres(command.numAncianosHombres())
                .numAncianasMujeres(command.numAncianasMujeres())
                .numNinos(command.numNinos())
                .numNinas(command.numNinas())
                .numHabitaciones(command.numHabitaciones())
                .numCamas(command.numCamas())
                .tieneAgua(command.tieneAgua())
                .tieneLuz(command.tieneLuz())
                .tieneAlcantarillado(command.tieneAlcantarillado())
                .tieneGas(command.tieneGas())
                .tieneOtrosServicios(command.tieneOtrosServicios())
                .nombreSensador(command.nombreSensador())
                .build();

        updateCensoPort.update(updatedCenso);
        return updatedCenso;
    }
}
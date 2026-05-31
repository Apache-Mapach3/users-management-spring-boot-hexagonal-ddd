package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateCensoUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveCensoPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateCensoCommand;
import com.jcaa.usersmanagement.domain.model.CensoModel;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class CreateCensoService implements CreateCensoUseCase {

    private final SaveCensoPort saveCensoPort;

    public CreateCensoService(final SaveCensoPort saveCensoPort) {
        this.saveCensoPort = Objects.requireNonNull(saveCensoPort, "El saveCensoPort no puede ser nulo");
    }

    @Override
    public CensoModel execute(CreateCensoCommand command) {
        CensoModel censo = CensoModel.create(
                command.nombre(), command.pais(), command.departamento(), command.ciudad(), command.casa(),
                command.numHombres(), command.numMujeres(), command.numAncianosHombres(), command.numAncianasMujeres(),
                command.numNinos(), command.numNinas(), command.numHabitaciones(), command.numCamas(),
                command.tieneAgua(), command.tieneLuz(), command.tieneAlcantarillado(),
                command.tieneGas(), command.tieneOtrosServicios(), command.nombreSensador()
        );

        saveCensoPort.save(censo);
        return censo;
    }
}
package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateCensoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateCensoCommand;
import com.jcaa.usersmanagement.domain.model.CensoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateCensoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateCensoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.CensoRestResponse;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CensoRestMapper {

    public static CreateCensoCommand toCreateCommand(CreateCensoRestRequest request) {
        return new CreateCensoCommand(
                request.nombre(), request.pais(), request.departamento(), request.ciudad(), request.casa(),
                request.numHombres(), request.numMujeres(), request.numAncianosHombres(), request.numAncianasMujeres(),
                request.numNinos(), request.numNinas(), request.numHabitaciones(), request.numCamas(),
                request.tieneAgua(), request.tieneLuz(), request.tieneAlcantarillado(),
                request.tieneGas(), request.tieneOtrosServicios(), request.nombreSensador()
        );
    }

    public static UpdateCensoCommand toUpdateCommand(String id, UpdateCensoRestRequest request) {
        return new UpdateCensoCommand(
                id, request.nombre(), request.pais(), request.departamento(), request.ciudad(), request.casa(),
                request.numHombres(), request.numMujeres(), request.numAncianosHombres(), request.numAncianasMujeres(),
                request.numNinos(), request.numNinas(), request.numHabitaciones(), request.numCamas(),
                request.tieneAgua(), request.tieneLuz(), request.tieneAlcantarillado(),
                request.tieneGas(), request.tieneOtrosServicios(), request.nombreSensador()
        );
    }

    public static CensoRestResponse toResponse(CensoModel model) {
        return new CensoRestResponse(
                model.getCensoId().value().toString(), model.getNombre(), model.getFecha(),
                model.getPais(), model.getDepartamento(), model.getCiudad(), model.getCasa(),
                model.getNumHombres(), model.getNumMujeres(), model.getNumAncianosHombres(), model.getNumAncianasMujeres(),
                model.getNumNinos(), model.getNumNinas(), model.getNumHabitaciones(), model.getNumCamas(),
                model.isTieneAgua(), model.isTieneLuz(), model.isTieneAlcantarillado(),
                model.isTieneGas(), model.isTieneOtrosServicios(), model.getNombreSensador()
        );
    }

    public static List<CensoRestResponse> toResponseList(List<CensoModel> models) {
        return models.stream().map(CensoRestMapper::toResponse).toList();
    }
}
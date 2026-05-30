package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.CensoModel;
import com.jcaa.usersmanagement.domain.valueobject.CensoId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.CensoEntity;

public class CensoPersistenceMapper {


    public static CensoModel toModel(CensoEntity entity) {
        if (entity == null) {
            return null;
        }

        return CensoModel.builder()
                .id(CensoId.fromString(entity.getId()))
                .nombre(entity.getNombre())
                .fecha(entity.getFecha())
                .pais(entity.getPais())
                .departamento(entity.getDepartamento())
                .ciudad(entity.getCiudad())
                .casa(entity.getCasa())
                .numHombres(entity.getNumHombres())
                .numMujeres(entity.getNumMujeres())
                .numAncianosHombres(entity.getNumAncianosHombres())
                .numAncianasMujeres(entity.getNumAncianasMujeres())
                .numNinos(entity.getNumNinos())
                .numNinas(entity.getNumNinas())
                .numHabitaciones(entity.getNumHabitaciones())
                .numCamas(entity.getNumCamas())
                .tieneAgua(entity.isTieneAgua())
                .tieneLuz(entity.isTieneLuz())
                .tieneAlcantarillado(entity.isTieneAlcantarillado())
                .tieneGas(entity.isTieneGas())
                .tieneOtrosServicios(entity.isTieneOtrosServicios())
                .nombreSensador(entity.getNombreSensador())
                .build();
    }

    public static CensoEntity toEntity(CensoModel model) {
        if (model == null) {
            return null;
        }

        return CensoEntity.builder()
                .id(model.getId().value().toString())
                .nombre(model.getNombre())
                .fecha(model.getFecha())
                .pais(model.getPais())
                .departamento(model.getDepartamento())
                .ciudad(model.getCiudad())
                .casa(model.getCasa())
                .numHombres(model.getNumHombres())
                .numMujeres(model.getNumMujeres())
                .numAncianosHombres(model.getNumAncianosHombres())
                .numAncianasMujeres(model.getNumAncianasMujeres())
                .numNinos(model.getNumNinos())
                .numNinas(model.getNumNinas())
                .numHabitaciones(model.getNumHabitaciones())
                .numCamas(model.getNumCamas())
                .tieneAgua(model.isTieneAgua())
                .tieneLuz(model.isTieneLuz())
                .tieneAlcantarillado(model.isTieneAlcantarillado())
                .tieneGas(model.isTieneGas())
                .tieneOtrosServicios(model.isTieneOtrosServicios())
                .nombreSensador(model.getNombreSensador())
                .build();
    }
}
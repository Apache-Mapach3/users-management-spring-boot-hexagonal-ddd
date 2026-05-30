package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response;

import java.time.LocalDate;

public record CensoRestResponse(
        String id, String nombre, LocalDate fecha, String pais, String departamento,
        String ciudad, String casa, int numHombres, int numMujeres,
        int numAncianosHombres, int numAncianasMujeres, int numNinos, int numNinas,
        int numHabitaciones, int numCamas, boolean tieneAgua, boolean tieneLuz,
        boolean tieneAlcantarillado, boolean tieneGas, boolean tieneOtrosServicios,
        String nombreSensador
) {}

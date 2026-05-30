package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateCensoRestRequest(
        @NotBlank String nombre, @NotBlank String pais, @NotBlank String departamento,
        @NotBlank String ciudad, @NotBlank String casa,
        int numHombres, int numMujeres, int numAncianosHombres, int numAncianasMujeres,
        int numNinos, int numNinas, int numHabitaciones, int numCamas,
        boolean tieneAgua, boolean tieneLuz, boolean tieneAlcantarillado,
        boolean tieneGas, boolean tieneOtrosServicios, @NotBlank String nombreSensador
) {}

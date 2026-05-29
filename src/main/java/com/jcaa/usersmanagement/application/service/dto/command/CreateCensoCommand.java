package com.jcaa.usersmanagement.application.service.dto.command;

public record CreateCensoCommand(
        String nombre, String pais, String departamento, String ciudad, String casa,
        int numHombres, int numMujeres, int numAncianosHombres, int numAncianasMujeres,
        int numNinos, int numNinas, int numHabitaciones, int numCamas,
        boolean tieneAgua, boolean tieneLuz, boolean tieneAlcantarillado,
        boolean tieneGas, boolean tieneOtrosServicios, String nombreSensador
) {}
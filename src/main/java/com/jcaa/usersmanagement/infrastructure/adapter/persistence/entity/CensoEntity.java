package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class CensoEntity {
    private String id;
    private String nombre;
    private LocalDate fecha;
    private String pais;
    private String departamento;
    private String ciudad;
    private String casa;
    private int numHombres;
    private int numMujeres;
    private int numAncianosHombres;
    private int numAncianasMujeres;
    private int numNinos;
    private int numNinas;
    private int numHabitaciones;
    private int numCamas;
    private boolean tieneAgua;
    private boolean tieneLuz;
    private boolean tieneAlcantarillado;
    private boolean tieneGas;
    private boolean tieneOtrosServicios;
    private String nombreSensador;
}
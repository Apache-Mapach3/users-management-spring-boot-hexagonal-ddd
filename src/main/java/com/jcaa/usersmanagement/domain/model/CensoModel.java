package com.jcaa.usersmanagement.domain.model;
import com.jcaa.usersmanagement.domain.valueobject.CensoId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class CensoModel {

    private CensoId id;
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

    public static CensoModel create(String nombre, String pais, String departamento, String ciudad, String casa,
                                    int numHombres, int numMujeres, int numAncianosHombres, int numAncianasMujeres,
                                    int numNinos, int numNinas, int numHabitaciones, int numCamas,
                                    boolean tieneAgua, boolean tieneLuz, boolean tieneAlcantarillado,
                                    boolean tieneGas, boolean tieneOtrosServicios, String nombreSensador) {
        return CensoModel.builder()
                .id(CensoId.generate())
                .fecha(LocalDate.now())
                .nombre(nombre)
                .pais(pais)
                .departamento(departamento)
                .ciudad(ciudad)
                .casa(casa)
                .numHombres(numHombres)
                .numMujeres(numMujeres)
                .numAncianosHombres(numAncianosHombres)
                .numAncianasMujeres(numAncianasMujeres)
                .numNinos(numNinos)
                .numNinas(numNinas)
                .numHabitaciones(numHabitaciones)
                .numCamas(numCamas)
                .tieneAgua(tieneAgua)
                .tieneLuz(tieneLuz)
                .tieneAlcantarillado(tieneAlcantarillado)
                .tieneGas(tieneGas)
                .tieneOtrosServicios(tieneOtrosServicios)
                .nombreSensador(nombreSensador)
                .build();
    }
}
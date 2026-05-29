package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.valueobject.CensoId;
import lombok.Builder;
import lombok.Value;
import java.time.LocalDate;

@Value
@Builder
public class CensoModel {
    CensoId censoId;
    String nombre;
    LocalDate fecha;
    String pais;
    String departamento;
    String ciudad;
    String casa;

    // Estadísticas demográficas
    int numHombres;
    int numMujeres;
    int numAncianosHombres;
    int numAncianasMujeres;
    int numNinos;
    int numNinas;

    // Habitabilidad
    int numHabitaciones;
    int numCamas;

    // Servicios públicos
    boolean tieneAgua;
    boolean tieneLuz;
    boolean tieneAlcantarillado;
    boolean tieneGas;
    boolean tieneOtrosServicios;

    // Responsable
    String nombreSensador;

    // Método estático de fábrica para crear un nuevo Censo (Genera el ID y la fecha)
    public static CensoModel create(
            String nombre, String pais, String departamento, String ciudad, String casa,
            int numHombres, int numMujeres, int numAncianosHombres, int numAncianasMujeres,
            int numNinos, int numNinas, int numHabitaciones, int numCamas,
            boolean tieneAgua, boolean tieneLuz, boolean tieneAlcantarillado,
            boolean tieneGas, boolean tieneOtrosServicios, String nombreSensador) {

        return CensoModel.builder()
                .censoId(CensoId.generate())
                .fecha(LocalDate.now()) // Se registra con la fecha actual
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

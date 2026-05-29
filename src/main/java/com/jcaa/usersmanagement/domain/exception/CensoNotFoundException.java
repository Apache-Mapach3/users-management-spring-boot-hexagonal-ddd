package com.jcaa.usersmanagement.domain.exception;

public class CensoNotFoundException extends DomainException {

    private CensoNotFoundException(String message) {
        super(message);
    }

    public static CensoNotFoundException becauseIdWasNotFound(String censoId) {
        return new CensoNotFoundException("No se encontró ningún censo con el ID: " + censoId);
    }
}

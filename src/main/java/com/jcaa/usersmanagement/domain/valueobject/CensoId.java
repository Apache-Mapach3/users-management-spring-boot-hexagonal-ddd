package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidCensoIdException;
import java.util.UUID;

public record CensoId(UUID value) {

    public CensoId {
        if (value == null) {
            throw new InvalidCensoIdException("El id del censo no puede ser nulo");
        }
    }

    public static CensoId generate() {
        return new CensoId(UUID.randomUUID());
    }

    public static CensoId fromString(String id) {
        try {
            return new CensoId(UUID.fromString(id));
        } catch (IllegalArgumentException e) {
            throw new InvalidCensoIdException("El formato del id del censo no es válido");
        }
    }
}
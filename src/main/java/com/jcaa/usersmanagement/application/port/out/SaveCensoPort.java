package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.CensoModel;

public interface SaveCensoPort {
    // Debe recibir un CensoModel. Puede devolver el modelo o ser 'void'
    CensoModel save(CensoModel censo);
}
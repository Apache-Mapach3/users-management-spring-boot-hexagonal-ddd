package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.CensoModel;

public interface SaveCensoPort {
    void execute(CensoModel censo);
}
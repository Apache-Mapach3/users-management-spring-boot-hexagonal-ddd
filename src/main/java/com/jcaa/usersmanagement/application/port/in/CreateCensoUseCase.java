package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateCensoCommand;
import com.jcaa.usersmanagement.domain.model.CensoModel;

public interface CreateCensoUseCase {
    CensoModel execute(CreateCensoCommand command);
}
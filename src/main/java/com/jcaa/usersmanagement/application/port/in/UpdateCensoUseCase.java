package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateCensoCommand;
import com.jcaa.usersmanagement.domain.model.CensoModel;

public interface UpdateCensoUseCase {
    CensoModel execute(UpdateCensoCommand command);
}
package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteCensoCommand;

public interface DeleteCensoUseCase {
    void execute(DeleteCensoCommand command);
}
package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetCensoByIdQuery;
import com.jcaa.usersmanagement.domain.model.CensoModel;

public interface GetCensoByIdUseCase {
    CensoModel execute(GetCensoByIdQuery query);
}
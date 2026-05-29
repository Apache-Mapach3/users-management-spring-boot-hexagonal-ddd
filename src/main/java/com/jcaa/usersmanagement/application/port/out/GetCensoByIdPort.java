package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.CensoModel;
import java.util.Optional;

public interface GetCensoByIdPort {
    Optional<CensoModel> execute(String censoId);
}
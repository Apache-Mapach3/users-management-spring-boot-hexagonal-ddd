package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.CensoModel;
import java.util.List;

public interface GetAllCensosUseCase {
    List<CensoModel> execute();
}
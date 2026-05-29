package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.CensoModel;
import java.util.List;

public interface GetAllCensosPort {
    List<CensoModel> findAll();
}
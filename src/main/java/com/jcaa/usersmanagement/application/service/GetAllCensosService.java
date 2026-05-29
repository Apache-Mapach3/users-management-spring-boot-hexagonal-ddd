package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllCensosUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllCensosPort;
import com.jcaa.usersmanagement.domain.model.CensoModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllCensosService implements GetAllCensosUseCase {

    private final GetAllCensosPort getAllCensosPort;

    @Override
    public List<CensoModel> execute() {
        return getAllCensosPort.execute();
    }
}
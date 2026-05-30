package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.application.port.in.*;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteCensoCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetCensoByIdQuery;
import com.jcaa.usersmanagement.domain.model.CensoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateCensoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateCensoRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.CensoRestResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper.CensoRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/censos")
@RequiredArgsConstructor
public class CensoRestController {

    private final CreateCensoUseCase createCensoUseCase;
    private final GetCensoByIdUseCase getCensoByIdUseCase;
    private final GetAllCensosUseCase getAllCensosUseCase;
    private final UpdateCensoUseCase updateCensoUseCase;
    private final DeleteCensoUseCase deleteCensoUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CensoRestResponse create(@Valid @RequestBody CreateCensoRestRequest request) {
        CensoModel censo = createCensoUseCase.execute(CensoRestMapper.toCreateCommand(request));
        return CensoRestMapper.toResponse(censo);
    }

    @GetMapping
    public List<CensoRestResponse> getAll() {
        return CensoRestMapper.toResponseList(getAllCensosUseCase.execute());
    }

    @GetMapping("/{id}")
    public CensoRestResponse getById(@PathVariable String id) {
        CensoModel censo = getCensoByIdUseCase.execute(new GetCensoByIdQuery(id));
        return CensoRestMapper.toResponse(censo);
    }

    @PutMapping("/{id}")
    public CensoRestResponse update(@PathVariable String id, @Valid @RequestBody UpdateCensoRestRequest request) {
        CensoModel censo = updateCensoUseCase.execute(CensoRestMapper.toUpdateCommand(id, request));
        return CensoRestMapper.toResponse(censo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        deleteCensoUseCase.execute(new DeleteCensoCommand(id));
    }
}
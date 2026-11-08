package com.pbo.exampleemployee.interfaces.rest.employee.controller;

import com.pbo.exampleemployee.application.employee.port.in.EmployeeUseCase;
import com.pbo.exampleemployee.interfaces.rest.employee.http.request.CreateEmployeeRequest;
import com.pbo.exampleemployee.interfaces.rest.employee.mapper.EmployeeRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeUseCase employeeUseCase;

    @PostMapping("/v1/employees")
    public ResponseEntity<Void> createEmployees(@Valid @RequestBody List<CreateEmployeeRequest> requests) {
        requests.forEach(request -> {
            var command = EmployeeRestMapper.toCommand(request);
            employeeUseCase.createEmployee(command);
        });
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
package com.pbo.exampleemployee.interfaces.rest.employee.controller;

import com.pbo.exampleemployee.application.employee.port.in.EmployeeUseCase;
import com.pbo.exampleemployee.interfaces.rest.employee.http.request.CreateEmployeeRequest;
import com.pbo.exampleemployee.interfaces.rest.employee.mapper.EmployeeRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Employees", description = "Operations for employee management")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class EmployeeController {

    private final EmployeeUseCase employeeUseCase;

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employees created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "409", description = "Employee already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Create employees", description = "Creates one or more employees from the provided request payload")
    @PostMapping("/employees")
    public ResponseEntity<Void> createEmployees(@Valid @RequestBody List<CreateEmployeeRequest> requests) {
        requests.forEach(request -> {
            var command = EmployeeRestMapper.toCommand(request);
            employeeUseCase.createEmployee(command);
        });
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
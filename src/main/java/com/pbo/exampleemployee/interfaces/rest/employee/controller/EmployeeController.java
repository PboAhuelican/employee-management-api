package com.pbo.exampleemployee.interfaces.rest.employee.controller;

import com.pbo.exampleemployee.application.employee.command.UpdateEmployeeCommand;
import com.pbo.exampleemployee.application.employee.port.in.EmployeeUseCase;
import com.pbo.exampleemployee.domain.employee.model.Employee;
import com.pbo.exampleemployee.interfaces.rest.employee.http.request.CreateEmployeeRequest;
import com.pbo.exampleemployee.interfaces.rest.employee.http.request.UpdateEmployeeRequest;
import com.pbo.exampleemployee.interfaces.rest.employee.http.response.EmployeeResponse;
import com.pbo.exampleemployee.interfaces.rest.employee.mapper.EmployeeRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Tag(name = "Employees", description = "Operations for employee management")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class EmployeeController {

    private final EmployeeUseCase employeeUseCase;

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employees Get successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Get employees", description = "Gets all the employees")
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeUseCase.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee found successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "400", description = "Invalid ID format"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Get employee by ID", description = "Retrieves a single employee by their unique identifier")
    @GetMapping("employees/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable UUID id) {
        Employee employee = employeeUseCase.getEmployeeById(id);
        return ResponseEntity.ok(EmployeeRestMapper.toResponse(employee));
    }

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

    @Operation(summary = "Partially or fully update an employee",
            description = "Updates one or more employee attributes using the provided identifier"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("employees/{id}")
    public ResponseEntity<Void> updateEmployee(
            @PathVariable UUID id,
            @RequestBody UpdateEmployeeRequest request) {
        UpdateEmployeeCommand command = EmployeeRestMapper.toCommand(id, request);
        employeeUseCase.updateEmployee(command);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete employee", description = "Deletes an existing employee identified by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {

        employeeUseCase.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Search employees by name", description = "Searches employees whose first name contains the provided value")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employees retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid search parameter"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping("employees/search")
    public ResponseEntity<List<EmployeeResponse>>
    searchEmployeesByName(@RequestParam String name) {
        return ResponseEntity.ok(EmployeeRestMapper.toResponseList(employeeUseCase.searchEmployeesByName(name)));
    }

}
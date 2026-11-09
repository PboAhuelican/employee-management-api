package com.pbo.exampleemployee.interfaces.rest.employee.mapper;

import com.pbo.exampleemployee.application.employee.command.CreateEmployeeCommand;
import com.pbo.exampleemployee.application.employee.command.UpdateEmployeeCommand;
import com.pbo.exampleemployee.domain.employee.model.Employee;
import com.pbo.exampleemployee.interfaces.rest.employee.http.request.CreateEmployeeRequest;
import com.pbo.exampleemployee.interfaces.rest.employee.http.request.UpdateEmployeeRequest;
import com.pbo.exampleemployee.interfaces.rest.employee.http.response.EmployeeResponse;

import java.util.List;
import java.util.UUID;

public class EmployeeRestMapper {

    public static CreateEmployeeCommand toCommand(
            CreateEmployeeRequest request) {

        return new CreateEmployeeCommand(
                request.firstName(),
                request.middleName(),
                request.paternalLastName(),
                request.maternalLastName(),
                request.age(),
                request.gender(),
                request.birthDate(),
                request.jobTitle()
        );
    }
    public static EmployeeResponse toResponse(
            Employee employee) {

        return new EmployeeResponse(
                employee.id(),
                employee.firstName().value(),
                employee.middleName().value(),
                employee.paternalLastName().value(),
                employee.maternalLastName().value(),
                employee.age(),
                employee.gender(),
                employee.birthDate(),
                employee.jobTitle().value(),
                employee.status()
        );
    }
    public static UpdateEmployeeCommand toCommand(UUID id, UpdateEmployeeRequest request) {
        return new UpdateEmployeeCommand(
                id,
                request.firstName(),
                request.middleName(),
                request.paternalLastName(),
                request.maternalLastName(),
                request.age(),
                request.gender(),
                request.birthDate(),
                request.jobTitle(),
                request.status()
        );
    }

    public static List<EmployeeResponse> toResponseList(
            List<Employee> employees) {

        return employees.stream()
                .map(EmployeeRestMapper::toResponse)
                .toList();
    }
}

package com.pbo.exampleemployee.interfaces.rest.employee.mapper;

import com.pbo.exampleemployee.application.employee.command.CreateEmployeeCommand;
import com.pbo.exampleemployee.interfaces.rest.employee.http.request.CreateEmployeeRequest;

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
}

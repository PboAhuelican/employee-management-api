package com.pbo.exampleemployee.application.employee.port.in;

import com.pbo.exampleemployee.domain.employee.model.Employee;

import java.util.UUID;

public interface GetEmployeeByIdUseCase {
    Employee getEmployeeById(UUID id);
}

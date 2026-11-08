package com.pbo.exampleemployee.application.employee.port.in;

import com.pbo.exampleemployee.application.employee.command.CreateEmployeeCommand;
import com.pbo.exampleemployee.domain.employee.model.Employee;

public interface CreateEmployeeUseCase {
    Employee createEmployee(CreateEmployeeCommand command);
}

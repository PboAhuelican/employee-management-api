package com.pbo.exampleemployee.application.employee.port.in;

import com.pbo.exampleemployee.application.employee.command.UpdateEmployeeCommand;

public interface UpdateEmployeeUseCase {
    void updateEmployee(UpdateEmployeeCommand command);
}

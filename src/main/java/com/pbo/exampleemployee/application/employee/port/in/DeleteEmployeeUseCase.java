package com.pbo.exampleemployee.application.employee.port.in;

import java.util.UUID;

public interface DeleteEmployeeUseCase {
    void deleteEmployee(UUID id);
}

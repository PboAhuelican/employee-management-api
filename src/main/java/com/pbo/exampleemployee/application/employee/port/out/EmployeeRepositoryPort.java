package com.pbo.exampleemployee.application.employee.port.out;

import com.pbo.exampleemployee.domain.employee.model.Employee;

public interface EmployeeRepositoryPort {
    Employee save(Employee employee);
}

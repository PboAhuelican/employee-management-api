package com.pbo.exampleemployee.application.employee.port.in;

import com.pbo.exampleemployee.domain.employee.model.Employee;

import java.util.List;

public interface SearchEmployeesByNameUseCase {
    List<Employee> searchEmployeesByName(String name);
}

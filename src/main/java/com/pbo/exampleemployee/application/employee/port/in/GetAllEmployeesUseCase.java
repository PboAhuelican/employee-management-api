package com.pbo.exampleemployee.application.employee.port.in;

import com.pbo.exampleemployee.domain.employee.model.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetAllEmployeesUseCase {
    List<Employee> getAllEmployees();
}

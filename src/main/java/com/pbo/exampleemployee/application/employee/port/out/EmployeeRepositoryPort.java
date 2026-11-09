package com.pbo.exampleemployee.application.employee.port.out;

import com.pbo.exampleemployee.domain.employee.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepositoryPort {
    Employee save(Employee employee);
    List<Employee> findAll();
    Optional<Employee> findById(UUID id);
    void deleteById(UUID id);
    List<Employee> findByName(String name);
}

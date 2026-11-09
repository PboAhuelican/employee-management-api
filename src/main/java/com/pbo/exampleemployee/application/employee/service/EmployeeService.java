package com.pbo.exampleemployee.application.employee.service;

import com.pbo.exampleemployee.application.employee.command.CreateEmployeeCommand;
import com.pbo.exampleemployee.application.employee.command.UpdateEmployeeCommand;
import com.pbo.exampleemployee.application.employee.port.in.EmployeeUseCase;
import com.pbo.exampleemployee.application.employee.port.out.EmployeeRepositoryPort;
import com.pbo.exampleemployee.application.exception.EmployeeNotFoundException;
import com.pbo.exampleemployee.domain.employee.model.Employee;
import com.pbo.exampleemployee.domain.employee.model.Status;
import com.pbo.exampleemployee.domain.employee.model.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class EmployeeService implements EmployeeUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepositoryPort employeeRepositoryPort;

    public EmployeeService(EmployeeRepositoryPort employeeRepositoryPort) {
        this.employeeRepositoryPort = employeeRepositoryPort;
    }


    @Override
    public Employee createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(
                UUID.randomUUID(),
                new FirstName(command.firstName()),
                new MiddleName(command.middleName()),
                new PaternalLastName(command.paternalLastName()),
                new MaternalLastName(command.maternalLastName()),
                command.age(),
                command.gender(),
                command.birthDate(),
                new JobTitle(command.jobTitle()),
                LocalDateTime.now(),
                Status.ACTIVE
        );
        employeeRepositoryPort.save(employee);

        LOGGER.info("create User {}", employee);
        return employee;
    }


    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepositoryPort.findAll();

        if (employees.isEmpty()) {
            LOGGER.warn("No exist employees");
            throw new EmployeeNotFoundException("No employees found");
        }
        return employees;
    }


    @Override
    public Employee getEmployeeById(UUID id) {
        return employeeRepositoryPort.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found"
                        )
                );
    }

    @Override
    public void updateEmployee(UpdateEmployeeCommand command) {

        //check if it exists before modifying
        Employee employee = employeeRepositoryPort.findById(command.id())
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee with id %s not found".formatted(command.id())
                        ));

        Employee updatedEmployee = new Employee(
                employee.id(),
                new FirstName(command.firstName()),
                new MiddleName(command.middleName()),
                new PaternalLastName(command.paternalLastName()),
                new MaternalLastName(command.maternalLastName()),
                command.age(),
                command.gender(),
                command.birthDate(),
                new JobTitle(command.jobTitle()),
                employee.registrationDate(),
                command.status()
        );

        employeeRepositoryPort.save(updatedEmployee);
    }

    @Override
    public void deleteEmployee(UUID id) {
        //check if it exists before deleting
        employeeRepositoryPort.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        employeeRepositoryPort.deleteById(id);
    }

    @Override
    public List<Employee> searchEmployeesByName(String name) {

        List<Employee> employees =
                employeeRepositoryPort.findByName(name);

        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException(
                    "No employees found matching the provided name: " + name
            );
        }
        return employees;
    }

}

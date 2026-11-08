package com.pbo.exampleemployee.application.employee.service;

import com.pbo.exampleemployee.application.employee.command.CreateEmployeeCommand;
import com.pbo.exampleemployee.application.employee.port.in.EmployeeUseCase;
import com.pbo.exampleemployee.application.employee.port.out.EmployeeRepositoryPort;
import com.pbo.exampleemployee.domain.employee.model.Employee;
import com.pbo.exampleemployee.domain.employee.model.Status;
import com.pbo.exampleemployee.domain.employee.model.vo.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EmployeeService implements EmployeeUseCase {

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
        return employee;
    }
}

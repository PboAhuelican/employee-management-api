package com.pbo.exampleemployee.application.employee.service;

import com.pbo.exampleemployee.application.employee.command.CreateEmployeeCommand;
import com.pbo.exampleemployee.application.employee.command.UpdateEmployeeCommand;
import com.pbo.exampleemployee.application.employee.port.out.EmployeeRepositoryPort;
import com.pbo.exampleemployee.application.exception.EmployeeNotFoundException;
import com.pbo.exampleemployee.domain.employee.model.Employee;
import com.pbo.exampleemployee.domain.employee.model.Gender;
import com.pbo.exampleemployee.domain.employee.model.Status;
import com.pbo.exampleemployee.domain.employee.model.vo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepositoryPort employeeRepositoryPort;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee buildEmployee() {
        return new Employee(
                UUID.randomUUID(),
                new FirstName("Pablo"),
                new MiddleName("Rodriguez"),
                new PaternalLastName("Rodriguez"),
                new MaternalLastName("Ahuelican"),
                34,
                Gender.MALE,
                LocalDate.of(1992, 5, 1),
                new JobTitle("Developer"),
                LocalDateTime.now(),
                Status.ACTIVE
        );
    }

    @Test
    void shouldReturnAllEmployees() {

        Employee employee = buildEmployee();

        when(employeeRepositoryPort.findAll())
                .thenReturn(List.of(employee));

        List<Employee> result =
                employeeService.getAllEmployees();

        assertEquals(1, result.size());

        verify(employeeRepositoryPort)
                .findAll();
    }

    @Test
    void shouldThrowExceptionWhenNoEmployeesExist() {

        when(employeeRepositoryPort.findAll())
                .thenReturn(List.of());

        assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.getAllEmployees()
        );
    }

    @Test
    void shouldReturnEmployeeById() {

        UUID id = UUID.randomUUID();

        Employee employee = buildEmployee();

        when(employeeRepositoryPort.findById(id))
                .thenReturn(Optional.of(employee));

        Employee result =
                employeeService.getEmployeeById(id);

        assertNotNull(result);

        verify(employeeRepositoryPort)
                .findById(id);
    }

    @Test
    void shouldThrowExceptionWhenEmployeeDoesNotExist() {

        UUID id = UUID.randomUUID();

        when(employeeRepositoryPort.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.getEmployeeById(id)
        );
    }

    @Test
    void shouldDeleteEmployee() {

        UUID id = UUID.randomUUID();

        Employee employee = buildEmployee();

        when(employeeRepositoryPort.findById(id))
                .thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(id);

        verify(employeeRepositoryPort)
                .deleteById(id);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingEmployee() {

        UUID id = UUID.randomUUID();

        when(employeeRepositoryPort.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.deleteEmployee(id)
        );
    }

    @Test
    void shouldSearchEmployeesByName() {

        Employee employee = buildEmployee();

        when(employeeRepositoryPort.findByName("Pablo"))
                .thenReturn(List.of(employee));

        List<Employee> result =
                employeeService.searchEmployeesByName("Pablo");

        assertEquals(1, result.size());

        verify(employeeRepositoryPort)
                .findByName("Pablo");
    }

    @Test
    void shouldThrowExceptionWhenSearchReturnsNoResults() {

        when(employeeRepositoryPort.findByName("abc"))
                .thenReturn(List.of());

        assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.searchEmployeesByName("abc")
        );
    }

    @Test
    void shouldCreateEmployee() {

        CreateEmployeeCommand command =
                new CreateEmployeeCommand(
                        "Pablo",
                        "Rodriguez",
                        "Rodriguez",
                        "Ahuelican",
                        34,
                        Gender.MALE,
                        LocalDate.of(1992, 5, 1),
                        "Developer"
                );

        Employee employee =
                employeeService.createEmployee(command);

        assertNotNull(employee);

        verify(employeeRepositoryPort)
                .save(any(Employee.class));
    }

    @Test
    void shouldUpdateEmployee() {

        UUID id = UUID.randomUUID();

        Employee employee = buildEmployee();

        UpdateEmployeeCommand command =
                new UpdateEmployeeCommand(
                        id,
                        "Juan",
                        "Carlos",
                        "Perez",
                        "Lopez",
                        35,
                        Gender.MALE,
                        LocalDate.of(1991, 5, 1),
                        "Senior Developer",
                        Status.ACTIVE
                );

        when(employeeRepositoryPort.findById(id))
                .thenReturn(Optional.of(employee));

        employeeService.updateEmployee(command);

        verify(employeeRepositoryPort)
                .save(any(Employee.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingEmployee() {

        UUID id = UUID.randomUUID();

        UpdateEmployeeCommand command =
                new UpdateEmployeeCommand(
                        id,
                        "Juan",
                        "Carlos",
                        "Perez",
                        "Lopez",
                        35,
                        Gender.MALE,
                        LocalDate.of(1991, 5, 1),
                        "Senior Developer",
                        Status.ACTIVE
                );

        when(employeeRepositoryPort.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.updateEmployee(command)
        );
    }

}
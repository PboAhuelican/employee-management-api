package com.pbo.exampleemployee.infrastructure.persistence.employee.adapter;

import com.pbo.exampleemployee.application.employee.port.out.EmployeeRepositoryPort;
import com.pbo.exampleemployee.domain.employee.model.Employee;
import com.pbo.exampleemployee.infrastructure.persistence.employee.entity.EmployeeEntity;
import com.pbo.exampleemployee.infrastructure.persistence.employee.mapper.EmployeePersistenceMapper;
import com.pbo.exampleemployee.infrastructure.persistence.employee.repository.JpaEmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Outbound adapter that implements the {@link EmployeeRepositoryPort}
 * and provides persistence capabilities for the Employee aggregate.
 **/
@Component
public class EmployeePersistenceAdapter implements EmployeeRepositoryPort {

    private final JpaEmployeeRepository jpaEmployeeRepository;

    public EmployeePersistenceAdapter(JpaEmployeeRepository jpaEmployeeRepository) {

        this.jpaEmployeeRepository = jpaEmployeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        EmployeeEntity entity =
                EmployeePersistenceMapper.toEntity(employee);

        EmployeeEntity saved = jpaEmployeeRepository.save(entity);

        return EmployeePersistenceMapper.toDomain(saved);
    }

    @Override
    public List<Employee> findAll() {
        return jpaEmployeeRepository.findAll()
                .stream()
                .map(EmployeePersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Employee> findById(UUID id) {
        return jpaEmployeeRepository.findById(id)
                .map(EmployeePersistenceMapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        jpaEmployeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findByName(String name) {

        return jpaEmployeeRepository
                .findByFirstNameContainingIgnoreCase(name)
                .stream()
                .map(EmployeePersistenceMapper::toDomain)
                .toList();
    }
}

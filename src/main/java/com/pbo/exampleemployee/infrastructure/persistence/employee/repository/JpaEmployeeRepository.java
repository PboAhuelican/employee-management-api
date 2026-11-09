package com.pbo.exampleemployee.infrastructure.persistence.employee.repository;

import com.pbo.exampleemployee.infrastructure.persistence.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaEmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {
    //to search by name you use the query method
    List<EmployeeEntity> findByFirstNameContainingIgnoreCase(String firstName);
}

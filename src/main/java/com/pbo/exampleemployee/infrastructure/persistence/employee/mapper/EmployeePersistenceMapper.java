package com.pbo.exampleemployee.infrastructure.persistence.employee.mapper;

import com.pbo.exampleemployee.domain.employee.model.Employee;
import com.pbo.exampleemployee.domain.employee.model.vo.*;
import com.pbo.exampleemployee.infrastructure.persistence.employee.entity.EmployeeEntity;

public class EmployeePersistenceMapper {


    public static EmployeeEntity toEntity(Employee employee) {

        EmployeeEntity entity = new EmployeeEntity();

        entity.setId(employee.id());
        entity.setFirstName(employee.firstName().value());
        entity.setMiddleName(
                employee.middleName() != null
                        ? employee.middleName().value()
                        : null
        );
        entity.setPaternalLastName(
                employee.paternalLastName().value()
        );
        entity.setMaternalLastName(
                employee.maternalLastName().value()
        );
        entity.setAge(employee.age());
        entity.setGender(employee.gender());
        entity.setBirthDate(employee.birthDate());
        entity.setJobTitle(employee.jobTitle().value());
        entity.setRegistrationDate(
                employee.registrationDate()
        );
        entity.setStatus(employee.status());

        return entity;
    }

    public static Employee toDomain(EmployeeEntity entity) {

        return new Employee(
                entity.getId(),
                new FirstName(entity.getFirstName()),
                new MiddleName(entity.getMiddleName()),
                new PaternalLastName(entity.getPaternalLastName()),
                new MaternalLastName(entity.getMaternalLastName()),
                entity.getAge(),
                entity.getGender(),
                entity.getBirthDate(),
                new JobTitle(entity.getJobTitle()),
                entity.getRegistrationDate(),
                entity.getStatus()
        );
    }
}

package com.pbo.exampleemployee.application.employee.command;

import com.pbo.exampleemployee.domain.employee.model.Gender;

import java.time.LocalDate;

/*
transports data internally by use case
 */
public record CreateEmployeeCommand(
        String firstName,
        String middleName,
        String paternalLastName,
        String maternalLastName,
        Integer age,
        Gender gender,
        LocalDate birthDate,
        String jobTitle
) {
}

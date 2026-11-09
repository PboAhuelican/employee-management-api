package com.pbo.exampleemployee.application.employee.command;

import com.pbo.exampleemployee.domain.employee.model.Gender;
import com.pbo.exampleemployee.domain.employee.model.Status;

import java.time.LocalDate;
import java.util.UUID;

/*
transports data internally by use case
 */
public record UpdateEmployeeCommand(
        UUID id,
        String firstName,
        String middleName,
        String paternalLastName,
        String maternalLastName,
        Integer age,
        Gender gender,
        LocalDate birthDate,
        String jobTitle,
        Status status
) {
}

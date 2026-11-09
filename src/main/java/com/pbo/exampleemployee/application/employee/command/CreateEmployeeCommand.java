package com.pbo.exampleemployee.application.employee.command;

import com.pbo.exampleemployee.domain.employee.model.Gender;
import com.pbo.exampleemployee.domain.employee.model.Status;
import com.pbo.exampleemployee.domain.employee.model.vo.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

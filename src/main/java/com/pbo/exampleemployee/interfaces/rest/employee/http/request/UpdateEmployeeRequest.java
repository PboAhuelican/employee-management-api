package com.pbo.exampleemployee.interfaces.rest.employee.http.request;

import com.pbo.exampleemployee.domain.employee.model.Gender;
import com.pbo.exampleemployee.domain.employee.model.Status;

import java.time.LocalDate;

public record UpdateEmployeeRequest(
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

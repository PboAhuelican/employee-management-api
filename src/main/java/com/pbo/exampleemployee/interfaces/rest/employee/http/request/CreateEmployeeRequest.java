package com.pbo.exampleemployee.interfaces.rest.employee.http.request;

import com.pbo.exampleemployee.domain.employee.model.Gender;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public record CreateEmployeeRequest(
        @NotBlank
        @Size(max = 100)
        String firstName,

        String middleName,

        @NotBlank
        @Size(max = 100)
        String paternalLastName,

        @Size(max = 100)
        String maternalLastName,

        @NotNull
        @Min(18)
        Integer age,

        @NotNull
        Gender gender,

        @NotNull
        LocalDate birthDate,

        @NotBlank
        @Size(max = 150)
        String jobTitle
) {
}

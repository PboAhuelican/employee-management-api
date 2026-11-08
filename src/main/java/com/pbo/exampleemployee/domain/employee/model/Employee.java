package com.pbo.exampleemployee.domain.employee.model;

import com.pbo.exampleemployee.domain.employee.model.vo.*;
import com.pbo.exampleemployee.domain.exception.InvalidEmployeeDataException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record Employee(
        UUID id,
        FirstName firstName,
        MiddleName middleName,
        PaternalLastName paternalLastName,
        MaternalLastName maternalLastName,
        Integer age,
        Gender gender,
        LocalDate birthDate,
        JobTitle jobTitle,
        LocalDateTime registrationDate,
        Status status
) {
    //Validations Domain
    public Employee {
        validateAge(age);
        validateGender(gender);
        validateBirthDate(birthDate);
        validateRegistrationDate(registrationDate);
        validateStatus(status);
    }
    private static void validateAge(Integer age) {

        if (age == null) {
            throw new InvalidEmployeeDataException("Age is required.");
        }

        if (age < 18) {
            throw new InvalidEmployeeDataException(
                    "Employee must be at least 18 years old."
            );
        }
    }

    private static void validateGender(Gender gender) {

        if (gender == null) {
            throw new InvalidEmployeeDataException("Gender is required.");
        }
    }

    private static void validateBirthDate(LocalDate birthDate) {

        if (birthDate == null) {
            throw new InvalidEmployeeDataException("Birth date is required.");
        }

        if (birthDate.isAfter(LocalDate.now())) {
            throw new InvalidEmployeeDataException(
                    "Birth date cannot be in the future."
            );
        }
    }

    private static void validateRegistrationDate(
            LocalDateTime registrationDate) {

        if (registrationDate == null) {
            throw new InvalidEmployeeDataException(
                    "Registration date is required."
            );
        }
    }

    private static void validateStatus(Status status) {

        if (status == null) {
            throw new InvalidEmployeeDataException(
                    "Status is required."
            );
        }
    }
}

package com.pbo.exampleemployee.domain.employee.model.vo;

import com.pbo.exampleemployee.domain.exception.InvalidEmployeeDataException;

public record PaternalLastName(String value) {

    public PaternalLastName {

        if (value == null || value.isBlank()) {
            throw new InvalidEmployeeDataException("Paternal last name is required.");
        }
    }
}
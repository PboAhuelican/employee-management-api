package com.pbo.exampleemployee.domain.employee.model.vo;

import com.pbo.exampleemployee.domain.exception.InvalidEmployeeDataException;

public record MaternalLastName(String value) {

    public MaternalLastName{

        if (value == null || value.isBlank()) {
            throw new InvalidEmployeeDataException("Maternal last name is required.");
        }
    }
}

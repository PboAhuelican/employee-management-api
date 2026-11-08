package com.pbo.exampleemployee.domain.employee.model.vo;

import com.pbo.exampleemployee.domain.exception.InvalidEmployeeDataException;

public record FirstName(String value) {

    public FirstName{
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidEmployeeDataException("First name is required.");
        }

        if (value.trim().length() < 2) {
            throw new InvalidEmployeeDataException(
                    "First name must contain at least 2 characters."
            );
        }
    }}

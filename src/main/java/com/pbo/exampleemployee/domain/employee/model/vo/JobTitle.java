package com.pbo.exampleemployee.domain.employee.model.vo;

import com.pbo.exampleemployee.domain.exception.InvalidEmployeeDataException;

public record JobTitle(String value) {

    public JobTitle {

        if (value == null || value.isBlank()) {
            throw new InvalidEmployeeDataException("Job title is required.");
        }
    }
}

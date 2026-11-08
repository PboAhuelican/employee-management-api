package com.pbo.exampleemployee.domain.employee.model.vo;

public record MiddleName(String value) {

    public MiddleName {

        if (value != null) {
            value = value.strip();

            if (value.length() == 1) {
                throw new IllegalArgumentException("Middle name must contain at least 2 characters.");
            }
        }
    }
}

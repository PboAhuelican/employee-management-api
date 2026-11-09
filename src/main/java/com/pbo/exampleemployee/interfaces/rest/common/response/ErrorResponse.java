package com.pbo.exampleemployee.interfaces.rest.common.response;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message
) {
}

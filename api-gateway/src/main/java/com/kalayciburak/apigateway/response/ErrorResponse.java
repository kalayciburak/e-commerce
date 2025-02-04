package com.kalayciburak.apigateway.response;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        LocalDateTime timestamp,
        String message
) {
}

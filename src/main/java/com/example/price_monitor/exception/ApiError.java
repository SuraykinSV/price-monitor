package com.example.price_monitor.exception;

import java.time.Instant;

public record ApiError(
        String code,
        String message,
        Instant timestamp
) {
}
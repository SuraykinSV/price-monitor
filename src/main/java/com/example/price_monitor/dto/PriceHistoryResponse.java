package com.example.price_monitor.dto;
import java.math.BigDecimal;
import java.time.Instant;

public record PriceHistoryResponse(
        Long id,
        BigDecimal price,
        Instant checkedAt
) {
}
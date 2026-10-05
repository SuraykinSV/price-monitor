package com.example.price_monitor.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record ItemResponse(
        UUID id,
        String url,
        String store,
        String name,
        BigDecimal currentPrice,
        String currency,
        boolean active,
        Instant lastCheckedAt,
        Instant createdAt
) {
}
package com.example.price_monitor.parser;

import java.math.BigDecimal;

public record ProductData(
        String name,
        BigDecimal price,
        String currency
) {
}
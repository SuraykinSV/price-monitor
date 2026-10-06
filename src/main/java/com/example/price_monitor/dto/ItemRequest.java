package com.example.price_monitor.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ItemRequest(

        @NotBlank
        @Size(max = 2048)
        String url,

        @NotBlank
        @Size(max = 50)
        String store,

        @NotBlank
        @Size(max = 500)
        String name,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = true)
        BigDecimal currentPrice,

        @NotBlank
        @Pattern(regexp = "[A-Z]{3}")
        String currency
) {
}
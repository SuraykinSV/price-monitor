package com.example.price_monitor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String url;

    @Column(nullable = false, length = 50)
    private String store;

    @Column(nullable = false, length = 500)
    private String name;

    @Column(name = "current_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal currentPrice;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @Column(name = "last_checked_at", nullable = false)
    private Instant lastCheckedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public ItemEntity(
            String url,
            String store,
            String name,
            BigDecimal currentPrice,
            String currency,
            Instant lastCheckedAt,
            Instant createdAt
    ) {
        this.url = url;
        this.store = store;
        this.name = name;
        this.currentPrice = currentPrice;
        this.currency = currency;
        this.lastCheckedAt = lastCheckedAt;
        this.createdAt = createdAt;
    }

    public void updatePrice(BigDecimal newPrice, Instant checkedAt) {
        this.currentPrice = newPrice;
        this.lastCheckedAt = checkedAt;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }
}
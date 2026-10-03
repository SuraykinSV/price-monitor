package com.example.price_monitor.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "price_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PriceHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private ItemEntity item;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

    @Column(name = "checked_at", nullable = false)
    private Instant checkedAt;

    public PriceHistoryEntity(
            ItemEntity item,
            BigDecimal price,
            Instant checkedAt
    ) {
        this.item = item;
        this.price = price;
        this.checkedAt = checkedAt;
    }
}
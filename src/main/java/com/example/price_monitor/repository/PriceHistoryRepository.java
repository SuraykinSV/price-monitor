package com.example.price_monitor.repository;

import com.example.price_monitor.entity.PriceHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PriceHistoryRepository
        extends JpaRepository<PriceHistoryEntity, Long> {

    List<PriceHistoryEntity> findByItemIdOrderByCheckedAtDesc(UUID itemId);
}
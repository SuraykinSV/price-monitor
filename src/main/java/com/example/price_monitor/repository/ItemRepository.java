package com.example.price_monitor.repository;

import com.example.price_monitor.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemRepository
        extends JpaRepository<ItemEntity, UUID> {
    boolean existsByUrl(String url);
    List<ItemEntity> findAllByActiveTrueOrderByCreatedAtDesc();
}
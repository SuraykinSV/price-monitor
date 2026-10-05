package com.example.price_monitor.mapper;

import com.example.price_monitor.dto.ItemResponse;
import com.example.price_monitor.entity.ItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemResponse toResponse(ItemEntity entity) {
        return new ItemResponse(
                entity.getId(),
                entity.getUrl(),
                entity.getStore(),
                entity.getName(),
                entity.getCurrentPrice(),
                entity.getCurrency(),
                entity.isActive(),
                entity.getLastCheckedAt(),
                entity.getCreatedAt()
        );
    }
}
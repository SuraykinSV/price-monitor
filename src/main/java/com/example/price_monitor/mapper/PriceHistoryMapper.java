package com.example.price_monitor.mapper;

import com.example.price_monitor.dto.PriceHistoryResponse;
import com.example.price_monitor.entity.PriceHistoryEntity;
import org.springframework.stereotype.Component;

@Component
public class PriceHistoryMapper {

    public PriceHistoryResponse toResponse(PriceHistoryEntity entity) {
        return new PriceHistoryResponse(
                entity.getId(),
                entity.getPrice(),
                entity.getCheckedAt()
        );
    }
}

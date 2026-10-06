package com.example.price_monitor.service;

import com.example.price_monitor.dto.ItemRequest;
import com.example.price_monitor.dto.ItemResponse;
import com.example.price_monitor.entity.ItemEntity;
import com.example.price_monitor.entity.PriceHistoryEntity;
import com.example.price_monitor.exception.ItemAlreadyExistsException;
import com.example.price_monitor.mapper.ItemMapper;
import com.example.price_monitor.repository.ItemRepository;
import com.example.price_monitor.repository.PriceHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final PriceHistoryRepository priceHistoryRepository;
    private final ItemMapper itemMapper;

    @Transactional(readOnly = true)
    public List<ItemResponse> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(itemMapper::toResponse)
                .toList();
    }
    @Transactional
    public ItemResponse createItem(ItemRequest request) {

        if (itemRepository.existsByUrl(request.url())) {
            throw new ItemAlreadyExistsException(request.url());
        }

        Instant now = Instant.now();

        ItemEntity item = new ItemEntity(
                request.url(),
                request.store(),
                request.name(),
                request.currentPrice(),
                request.currency(),
                now,
                now
        );

        ItemEntity savedItem = itemRepository.save(item);

        PriceHistoryEntity history = new PriceHistoryEntity(
                savedItem,
                request.currentPrice(),
                now
        );

        priceHistoryRepository.save(history);

        return itemMapper.toResponse(savedItem);
    }
}
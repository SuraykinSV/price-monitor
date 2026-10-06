package com.example.price_monitor.service;

import com.example.price_monitor.dto.ItemRequest;
import com.example.price_monitor.dto.ItemResponse;
import com.example.price_monitor.dto.PriceHistoryResponse;
import com.example.price_monitor.entity.ItemEntity;
import com.example.price_monitor.entity.PriceHistoryEntity;
import com.example.price_monitor.exception.ItemAlreadyExistsException;
import com.example.price_monitor.exception.ItemNotFoundException;
import com.example.price_monitor.mapper.ItemMapper;
import com.example.price_monitor.mapper.PriceHistoryMapper;
import com.example.price_monitor.repository.ItemRepository;
import com.example.price_monitor.repository.PriceHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final PriceHistoryRepository priceHistoryRepository;
    private final ItemMapper itemMapper;
    private final PriceHistoryMapper priceHistoryMapper;

    @Transactional(readOnly = true)
    public List<ItemResponse> getAllItems() {
        return itemRepository.findAllByActiveTrueOrderByCreatedAtDesc()
                .stream()
                .map(itemMapper::toResponse)
                .toList();
    }


    @Transactional(readOnly = true)
    public ItemResponse getItem(UUID id) {
        ItemEntity item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));

        return itemMapper.toResponse(item);
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

    @Transactional(readOnly = true)
    public List<PriceHistoryResponse> getPriceHistory(UUID id) {

        if (!itemRepository.existsById(id)) {
            throw new ItemNotFoundException(id);
        }

        return priceHistoryRepository
                .findByItemIdOrderByCheckedAtDesc(id)
                .stream()
                .map(priceHistoryMapper::toResponse)
                .toList();
    }
}
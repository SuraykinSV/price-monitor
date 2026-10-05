package com.example.price_monitor.service;

import com.example.price_monitor.dto.ItemResponse;
import com.example.price_monitor.mapper.ItemMapper;
import com.example.price_monitor.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Transactional(readOnly = true)
    public List<ItemResponse> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(itemMapper::toResponse)
                .toList();
    }
}
package com.example.price_monitor.controller;

import com.example.price_monitor.dto.ItemRequest;
import com.example.price_monitor.dto.ItemResponse;
import com.example.price_monitor.dto.PriceHistoryResponse;
import com.example.price_monitor.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemResponse> getAllItems() {
        return itemService.getAllItems();
    }
    @GetMapping("/{id}")
    public ItemResponse getItem(@PathVariable UUID id) {
        return itemService.getItem(id);
    }
    @GetMapping("/{id}/prices")
    public List<PriceHistoryResponse> getPriceHistory(
            @PathVariable UUID id
    ) {
        return itemService.getPriceHistory(id);
    }


    @PostMapping
    public ResponseEntity<ItemResponse> createItem(@Valid @RequestBody ItemRequest request) {
        ItemResponse response = itemService.createItem(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
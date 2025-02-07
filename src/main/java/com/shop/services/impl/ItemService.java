package com.shop.services.impl;

import com.shop.db.repositories.IItemRepository;
import com.shop.model.Item;
import com.shop.services.IItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ItemService implements IItemService {

    private final IItemRepository itemRepository;

    public List<Item> getAllItems() {
        List<Item> items = this.itemRepository.getAllItems();

        items.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getId() - o2.getId();
            }
        });
        return items;
    }

    public Optional<Item> checkIfAvailable(String id) {
        return this.getAllItems().stream()
                .filter(item -> Integer.parseInt(id) == item.getId())
                .filter(item ->item.getQuantity() > 0)
                .findFirst();
    }
}

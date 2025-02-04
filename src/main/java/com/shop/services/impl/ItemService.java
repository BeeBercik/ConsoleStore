package com.shop.services.impl;

import com.shop.model.Console;
import com.shop.model.Item;
import com.shop.model.Pad;
import com.shop.services.IItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ItemService implements IItemService {

    public List<Item> getAllItems(List<Console> consoles, List<Pad> pads) {
        List<Item> items = new ArrayList<>();
        items.addAll(consoles);
        items.addAll(pads);

        items.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getId() - o2.getId();
            }
        });

        return items;
    }
}

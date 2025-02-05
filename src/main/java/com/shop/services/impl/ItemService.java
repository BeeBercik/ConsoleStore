package com.shop.services.impl;

import com.shop.db.repositories.IConsoleRepository;
import com.shop.db.repositories.IPadRepository;
import com.shop.db.repositories.impl.ItemRepository;
import com.shop.model.Console;
import com.shop.model.Item;
import com.shop.model.Pad;
import com.shop.services.IItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ItemService implements IItemService {

    private final IConsoleRepository consoleRepository;
    private final IPadRepository padRepository;
    private final ItemRepository itemRepository;

    @Getter
    private List<Item> basket = new ArrayList<>();

    public List<Item> getAllItems() {
        List<Console> consoles = this.consoleRepository.getAllConsoles();
        List<Pad> pads = this.padRepository.getAllPads();

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

    public Optional<Item> checkItem(String id) {
        List<Item> items = this.getAllItems();

        for (Item item : items) {
            if(Integer.parseInt(id) == item.getId() &&
                    item.getQuantity() > 0) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public void addItemToBasket(Item item) {
        this.basket.add(item);
    }

    public boolean finalizeBasket() {
        if(this.basket.isEmpty()) return false;
        for(Item item : this.basket) {
            if(item.getQuantity() < 0) return false;
            this.itemRepository.decreaseItemQuantity(item.getId());
        }
        this.basket.clear();
        return true;
    }
}

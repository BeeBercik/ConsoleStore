package com.shop.services.impl;

import com.shop.db.repositories.IItemRepository;
import com.shop.db.repositories.impl.ItemRepository;
import com.shop.model.Item;
import com.shop.model.User;
import com.shop.services.IBasketService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BasketService implements IBasketService {

    @Getter
    private List<Item> basket = new ArrayList<>();

    private final IItemRepository itemRepository;

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

package com.shop.services.impl;

import com.shop.db.repositories.IItemRepository;
import com.shop.model.BasketItem;
import com.shop.model.Item;
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
    private final List<BasketItem> basket = new ArrayList<>();

    private final IItemRepository itemRepository;

    public boolean addItemToBasket(Item item, int quantity) {
        if(quantity > item.getQuantity()) return false;

        for(BasketItem basketItem : this.basket) {
            if(basketItem.getItem().getId() == item.getId()) {
                basketItem.increaseQuantity(quantity);
                return true;
            }
        }

        this.basket.add(new BasketItem(item, quantity));
        return true;
    }

    public boolean finalizeBasket() {
        if(this.basket.isEmpty()) return false;

        for(BasketItem basketItem : this.basket) {
            if(basketItem.getItem().getQuantity() < 0 ||
                    basketItem.getQuantity() > basketItem.getItem().getQuantity()) return false;
            this.itemRepository.decreaseItemQuantity(basketItem.getItem().getId(), basketItem.getQuantity());
        }
        this.basket.clear();

        return true;
    }

    public int calculateBasketPrice() {
        int sum = 0;
        for(BasketItem basketItem : this.basket) {
            sum += basketItem.getItem().getPrice() * basketItem.getQuantity();
        }
        return sum;
    }
}

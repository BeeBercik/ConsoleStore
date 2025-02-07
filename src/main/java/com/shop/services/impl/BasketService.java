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
    private List<BasketItem> basket = new ArrayList<>();

    private final IItemRepository itemRepository;

    public boolean addItemToBasket(Item item, int quantity) {
        if(quantity > item.getQuantity() )
            return false;

        this.basket.add(new BasketItem(item, 1));

        return true;
    }

    public boolean finalizeBasket() {
        if(this.basket.isEmpty()) return false;

        for(BasketItem basketItem : this.basket) {
            if(basketItem.getItem().getQuantity() < 0 ||
                    basketItem.getQuantity() > basketItem.getItem().getQuantity()) return false;
            this.itemRepository.decreaseItemQuantity(basketItem.getItem().getId());
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

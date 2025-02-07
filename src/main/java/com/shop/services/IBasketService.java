package com.shop.services;

import com.shop.model.BasketItem;
import com.shop.model.Item;

import java.util.List;

public interface IBasketService {
    boolean addItemToBasket(Item item, int quantity);
    boolean finalizeBasket();
    List<BasketItem> getBasket();
}

package com.shop.services;

import com.shop.model.Item;
import com.shop.model.User;

import java.util.List;

public interface IBasketService {
    void addItemToBasket(Item item);
    boolean finalizeBasket();
    List<Item> getBasket();
}

package com.shop.services;

import com.shop.model.Item;
import com.shop.model.User;

public interface IBasketService {
    void checkAndCreateUserBasket(User user);
    void addItemToBasket(Item item);
    boolean finalizeBasket();
}

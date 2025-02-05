package com.shop.services;

import com.shop.model.Item;

import java.util.List;
import java.util.Optional;

public interface IItemService {
    List<Item> getAllItems();
    Optional<Item> checkItem(String id);
    void addItemToBasket(Item item);
    boolean finalizeBasket();
    List<Item> getBasket();
}
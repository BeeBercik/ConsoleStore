package com.shop.db.repositories;

import com.shop.model.Item;

import java.util.List;

public interface IItemRepository {
    void decreaseItemQuantity(int id, int quantity);
    List<Item> getAllItems();
}

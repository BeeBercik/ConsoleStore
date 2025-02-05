package com.shop.db.repositories;

import com.shop.model.Item;

import java.util.List;

public interface IItemRepository {
    boolean decreaseItemQuantity(int id);
    List<Item> getAllItems();
}

package com.shop.services;

import com.shop.model.Console;
import com.shop.model.Item;
import com.shop.model.Pad;

import java.util.List;

public interface IItemService {
    List<Item> getAllItems(List<Console> consoles, List<Pad> pads);
}

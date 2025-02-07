package com.shop.gui;

import com.shop.model.BasketItem;
import com.shop.model.Item;
import com.shop.model.User;

import java.util.List;
import java.util.Optional;

public interface IGUI {
    void  listAllItems();
    String loginOrRegister();
    User askForLoginCredentials();
    Optional<User> askForRegisterCredentials();
    String showChoicesAndGetOne();
    void showAppMessage(String message);
    String selectItem();
    void showBasket(List<BasketItem> items);
    int askForQuantity();
}

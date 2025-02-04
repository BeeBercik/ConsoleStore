package com.shop.gui;

import com.shop.model.Console;
import com.shop.model.Item;
import com.shop.model.Pad;
import com.shop.model.User;

import java.util.List;
import java.util.Optional;

public interface IGUI {
    void  listAllItems(List<Item> items);
    String loginOrRegister();
    User askForLoginCredentials();
    Optional<User> askForRegisterCredentials();
    String showChoicesAndGetOne();
    void showAppMessage(String message)
;}

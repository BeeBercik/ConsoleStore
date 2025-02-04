package com.shop.gui;

import com.shop.model.User;

import java.util.Optional;

public interface IGUI {
    void  listAllItems();
    String loginOrRegister();
    User askForLoginCredentials();
    Optional<User> askForRegisterCredentials();
    String showChoicesAndGetOne();
    void showAppMessage(String message)
;}

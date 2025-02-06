package com.shop.services;

import com.shop.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> login();
    boolean register();
}

package com.shop.db.repositories;

import com.shop.model.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> getByLogin(String login);
    boolean persist(User user) throws IllegalArgumentException;
}

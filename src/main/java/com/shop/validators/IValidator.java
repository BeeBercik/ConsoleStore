package com.shop.validators;

import com.shop.model.User;

import java.util.Optional;

public interface IValidator {
    Optional<User> checkCredentials(User user);
}

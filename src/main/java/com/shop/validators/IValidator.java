package com.shop.validators;

import com.shop.model.User;

public interface IValidator {
    boolean checkCredentials(User user);
}

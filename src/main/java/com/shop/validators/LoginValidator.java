package com.shop.validators;

import com.shop.model.User;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator {
    public boolean checkCredentials(User user) {
        return user.getLogin().equals("admin") &&
                user.getPassword().equals("admin");
    }
}

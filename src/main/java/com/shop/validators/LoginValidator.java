package com.shop.validators;

import com.shop.db.repositories.UserRepository;
import com.shop.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoginValidator {
    private final UserRepository userRepository;

    public boolean checkCredentials(User user) {
        Optional<User> userBox = this.userRepository.getByLogin(user.getLogin());

        return userBox.isPresent() &&
                userBox.get().getPassword().equals(user.getPassword());
    }
}

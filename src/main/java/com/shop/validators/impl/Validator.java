package com.shop.validators.impl;

import com.shop.db.repositories.impl.UserRepository;
import com.shop.model.User;
import com.shop.validators.IValidator;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Validator implements IValidator {
    private final UserRepository userRepository;

    public boolean checkCredentials(User user) {
        Optional<User> userBox = this.userRepository.getByLogin(user.getLogin());

        return userBox.isPresent() &&
                BCrypt.checkpw(user.getPassword(), userBox.get().getPassword());
    }
}

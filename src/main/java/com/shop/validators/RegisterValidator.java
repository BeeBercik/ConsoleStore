package com.shop.validators;

import com.shop.db.repositories.UserRepository;
import com.shop.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterValidator {
    private final UserRepository userRepository;

    public boolean checkIfLoginIsFree(User user) {
        return this.userRepository.getByLogin(user.getLogin()).isEmpty();
    }
}

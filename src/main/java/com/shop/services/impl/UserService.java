package com.shop.services.impl;

import com.shop.db.repositories.IUserRepository;
import com.shop.gui.IGUI;
import com.shop.model.User;
import com.shop.services.IUserService;
import com.shop.validators.IValidator;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IValidator validator;
    private final IGUI gui;

    public Optional<User> login() {
        Optional<User> userBox = Optional.empty();
        for(int attempts = 0; attempts < 3 && userBox.isEmpty(); attempts++) {
            userBox = this.validator.checkCredentials(this.gui.askForLoginCredentials());
        }
        return userBox;
    }

    public boolean register() {
        try {
            Optional<User> user = this.gui.askForRegisterCredentials();
            if (user.isPresent())
                return this.userRepository.persist(new User(
                        user.get().getLogin(),
                        BCrypt.hashpw(user.get().getPassword(), BCrypt.gensalt())
                ));
        } catch(IllegalArgumentException e) {
            this.gui.showAppMessage("User login already exists!");
        }
        return false;
    }
}

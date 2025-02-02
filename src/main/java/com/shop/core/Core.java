package com.shop.core;

import com.shop.db.repositories.UserRepository;
import com.shop.gui.GUI;
import com.shop.model.User;
import com.shop.validators.LoginValidator;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Core {

    private final GUI gui;
    private final LoginValidator loginValidator;
    private final UserRepository userRepository;

    public void run() {
        String entryChoice = this.gui.entry();
        boolean correct = switch(entryChoice) {
            case "1" -> this.login();
            case "2" -> this.register();
            default -> false;
        };

        while(correct) {
            switch(this.gui.showChoicesAndGetOne()) {
                case "1":
                    System.out.println("You choose nr 1!");
                    break;
                case "2":
                    System.out.println("You choose nr 2!");
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Incorrect choice!");
            }
        }
    }

    public boolean login() {
        int attempts = 0;
        boolean correct = false;
        while(attempts < 3 && !correct) {
            attempts++;
            correct = this.loginValidator.checkCredentials(this.gui.askForLoginCredentials());
        }
        return correct;
    }

    public boolean register() {
        Optional<User> user = this.gui.askForRegisterCredentials();
        if(user.isPresent())
            return this.userRepository.persist(new User(
                    user.get().getLogin(),
                    BCrypt.hashpw(user.get().getPassword(), BCrypt.gensalt())
            ));
        return false;
    }
}

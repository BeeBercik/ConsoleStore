package com.shop.core;

import com.shop.db.repositories.UserRepository;
import com.shop.gui.GUI;
import com.shop.validators.LoginValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
            if(this.loginValidator.checkCredentials(
                    this.gui.askForLoginCredentials())) correct = true;
        }
        return correct;
    }

    public boolean register() {
        return this.userRepository.persist(this.gui.askForRegisterCredentials());
    }
}

package com.shop.core.impl;

import com.shop.core.ICore;
import com.shop.db.repositories.impl.ConsoleRepository;
import com.shop.db.repositories.impl.PadRepository;
import com.shop.db.repositories.impl.UserRepository;
import com.shop.gui.impl.GUI;
import com.shop.model.User;
import com.shop.validators.impl.Validator;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Core implements ICore {

    private final GUI gui;
    private final Validator validator;
    private final UserRepository userRepository;
    private final ConsoleRepository consoleRepository;
    private final PadRepository padRepository;

    public void run() {
        String entryChoice = this.gui.loginOrRegister();
        boolean correct = switch(entryChoice) {
            case "1" -> this.login();
            case "2" -> this.register();
            default -> false;
        };

        while(correct) {
            switch(this.gui.showChoicesAndGetOne()) {
                case "1":
                    this.gui.listAllItems(this.consoleRepository.getAllConsoles(),
                            this.padRepository.getAllPads());
                    break;
                case "2":
                    System.out.println("You choose nr 2!");
                    break;
                case "3":
                    this.gui.showAppMessage("Exiting application...");
                    return;
                default:
                    this.gui.showAppMessage("Incorrect choice!");
            }
        }
        this.gui.showAppMessage("Not logged in! Exiting application...");
    }

    public boolean login() {
        int attempts = 0;
        boolean correct = false;
        while(attempts < 3 && !correct) {
            attempts++;
            correct = this.validator.checkCredentials(this.gui.askForLoginCredentials());
        }
        return correct;
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

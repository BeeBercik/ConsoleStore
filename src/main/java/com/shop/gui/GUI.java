package com.shop.gui;

import com.shop.model.User;
import com.shop.validators.RegisterValidator;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class GUI {
    private final Scanner scanner = new Scanner(System.in);
    private final RegisterValidator registerValidator;

    public String entry() {
        System.out.println("\n***Welcome to the grocery***");
        System.out.println("1. Log in");
        System.out.println("2. Register");

        return this.scanner.nextLine();
    }

    public User askForLoginCredentials() {
        System.out.print("Login: ");
        String login = this.scanner.nextLine();
        System.out.print("Password: ");
        String password = this.scanner.nextLine();

        return new User(login, password);
    }

    public User askForRegisterCredentials() {
        System.out.print("Login: ");
        String login = this.scanner.nextLine();

        String password = "";
        String repPassword = "";
        boolean samePasswords = false;
        while (!samePasswords) {
            System.out.print("Password: ");
            password = this.scanner.nextLine();
            System.out.print("Repeat password:");
            repPassword = this.scanner.nextLine();

            if(this.registerValidator.hashAndValidatePasswords(password, repPassword))
                System.out.println("Passwords do not match. Try again.");
            else
                samePasswords = true;
        }
        return new User(login, BCrypt.hashpw(password, BCrypt.gensalt()));
    }

    public String showChoicesAndGetOne() {
        System.out.println("\n--Select what do you want to do--");
        System.out.println("1. List items");
        System.out.println("2. Add to your cart");
        System.out.println("3. Exit shop");
        System.out.print("Nr: ");

        return this.scanner.nextLine();
    }
}

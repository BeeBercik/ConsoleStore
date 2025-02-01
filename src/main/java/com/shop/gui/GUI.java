package com.shop.gui;

import com.shop.model.User;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GUI {
    private final Scanner scanner = new Scanner(System.in);

    public void welcome() {
        System.out.println("\n***Welcome to the grocery***");
        System.out.println("--Log in--");
    }

    public User askCredentials() {
        System.out.print("Login: ");
        String login = this.scanner.nextLine();
        System.out.print("Password: ");
        String password = this.scanner.nextLine();

        return new User(login, password);
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

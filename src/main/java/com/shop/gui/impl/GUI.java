package com.shop.gui.impl;

import com.shop.gui.IGUI;
import com.shop.model.Console;
import com.shop.model.Pad;
import com.shop.model.Item;
import com.shop.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GUI implements IGUI {
    private final Scanner scanner = new Scanner(System.in);

    public void listAllItems(List<Console> consoles, List<Pad> pads) {
        this.showAppMessage("Item list");
        List<Item> items = new ArrayList<>();
        items.addAll(consoles);
        items.addAll(pads);

        items.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getId() - o2.getId();
            }
        });

        items.forEach(System.out::println);
    }

    public String loginOrRegister() {
        System.out.println("\n***Welcome to the grocery***");
        System.out.println("1. Log in");
        System.out.println("2. Register");
        System.out.print("Nr: ");

        return this.scanner.nextLine();
    }

    public User askForLoginCredentials() {
        System.out.print("Login: ");
        String login = this.scanner.nextLine();
        System.out.print("Password: ");
        String password = this.scanner.nextLine();

        return new User(login, password);
    }

    public Optional<User> askForRegisterCredentials() {
        System.out.print("Login: ");
        String login = this.scanner.nextLine();

        String password = "";
        String repPassword = "";
        boolean samePasswords = false;
        int attempts = 0;
        while (!samePasswords && attempts < 3) {
            System.out.print("Password: ");
            password = this.scanner.nextLine();
            System.out.print("Repeat password: ");
            repPassword = this.scanner.nextLine();

            if(!password.equals(repPassword)) {
                this.showAppMessage("Passwords do not match. Try again.");
                attempts++;
            } else
                return Optional.of(new User(login, password));
        }
        return Optional.empty();
    }

    public String showChoicesAndGetOne() {
        System.out.println("\n--Select what do you want to do--");
        System.out.println("1. List items");
        System.out.println("2. Add item to your basket");
        System.out.println("3. Exit shop");
        System.out.print("Nr: ");

        return this.scanner.nextLine();
    }

    public String selectItem() {
        System.out.print("\nSelect item ID which you want to add to your basket: ");
        return this.scanner.nextLine();
    }

    public void showAppMessage(String message) {
        System.out.println("\n** " + message + " **");
    }
}

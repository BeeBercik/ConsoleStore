package com.shop.gui.impl;

import com.shop.gui.IGUI;
import com.shop.model.BasketItem;
import com.shop.model.Item;
import com.shop.model.User;
import com.shop.services.IItemService;
import com.shop.services.impl.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class GUI implements IGUI {

    private final IItemService itemService;
    private final BasketService basketService;
    private final Scanner scanner = new Scanner(System.in);

    public void listAllItems() {
        this.showAppMessage("Item list");
        this.itemService.getAllItems().forEach(System.out::println);
    }

    public String loginOrRegister() {
        System.out.println("\n***Welcome to the ConsoleStore***");
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

            if(password.equals(repPassword)) {
                return Optional.of(new User(login, password));
            }
            this.showAppMessage("Passwords do not match. Try again.");
            attempts++;
        }
        return Optional.empty();
    }

    public String showChoicesAndGetOne() {
        System.out.println("\n--Select what do you want to do--");
        System.out.println("1. List items");
        System.out.println("2. Add item to your basket");
        System.out.println("3. Show my basket");
        System.out.println("4. Finalize basket");
        System.out.println("5. Exit shop");
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

    public int askForQuantity() {
        System.out.print("Quantity: ");
        return Integer.parseInt(this.scanner.nextLine());
    }

    public void showBasket(List<BasketItem> items) {
        if(items.isEmpty()) {
            this.showAppMessage("Your basket is empty");
        }
        else {
            this.showAppMessage("Your basket");
            items.forEach(System.out::println);
            System.out.println("--Summary basket price: " + this.basketService.calculateBasketPrice());

        }
    }
}

package com.shop.core.impl;

import com.shop.core.ICore;

import com.shop.gui.IGUI;
import com.shop.gui.impl.GUI;
import com.shop.model.Item;
import com.shop.model.User;
import com.shop.services.IBasketService;
import com.shop.services.IItemService;
import com.shop.services.IUserService;
import com.shop.services.impl.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Core implements ICore {

    private final IGUI gui;
    private final IItemService itemService;
    private final IUserService userService;
    private final IBasketService basketService;

    public void run() {
        Optional<User> userBox = this.loginOrRegister();

        while(userBox.isPresent()) {
            switch(this.gui.showChoicesAndGetOne()) {
                case "1":
                    this.gui.listAllItems();
                    break;
                case "2":

                    Optional<Item> itemBox = this.itemService.checkItem(this.gui.selectItem());

                    if(itemBox.isPresent()) {
                        boolean result = this.basketService.addItemToBasket(itemBox.get(), this.gui.askForQuantity());
                        this.gui.showAppMessage(result ? "Item added to your basket" : "Item not added to your basket");
                    } else
                        this.gui.showAppMessage("Item not added.");

                    break;
                case "3":
                    this.gui.showBasket(this.basketService.getBasket());
                    break;
                case "4":
                    this.gui.showAppMessage(this.basketService.finalizeBasket() ? "Congratulations, you finalized your shopping" : "You cant finalize your basket");
                    break;
                case "5":
                    this.gui.showAppMessage("Exiting application...");
                    return;
                default:
                    this.gui.showAppMessage("Incorrect choice!");
            }
        }
        this.gui.showAppMessage("Not logged in! Exiting application...");
    }

    public Optional<User> loginOrRegister() {
        String entryChoice = this.gui.loginOrRegister();

        Optional<User> userBox = Optional.empty();
        switch(entryChoice) {
            case "1":
                userBox = this.userService.login();
                break;
            case "2":
                if(this.userService.register()) {
                    this.gui.showAppMessage("Registered successfully! Now you can log in");
                    this.run();
                }
                break;
            default:
                this.gui.showAppMessage("Incorrect choice");
        }

        return userBox;
    }
}

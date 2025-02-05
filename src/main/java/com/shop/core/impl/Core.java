package com.shop.core.impl;

import com.shop.core.ICore;

import com.shop.gui.impl.GUI;
import com.shop.model.Item;
import com.shop.services.impl.ItemService;
import com.shop.services.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Core implements ICore {

    private final GUI gui;
    private final ItemService itemService;
    private final UserService userService;

    public void run() {
        String entryChoice = this.gui.loginOrRegister();
        boolean correct = switch(entryChoice) {
            case "1" -> this.userService.login();
            case "2" -> this.userService.register();
            default -> false;
        };

        while(correct) {
            switch(this.gui.showChoicesAndGetOne()) {
                case "1":
                    this.gui.listAllItems();
                    break;
                case "2":
                    String id = this.gui.selectItem();
                    Optional<Item> itemBox = this.itemService.checkItem(id);
                    if(itemBox.isPresent()) {
                        this.itemService.addItemToBasket(itemBox.get());
                        this.gui.showAppMessage("Item added to your basket");
                    }
                    else this.gui.showAppMessage("Item not added.");
                    break;
                case "3":
                    this.gui.showBasket(this.itemService.getBasket());
                    break;
                case "4":
                    this.gui.showAppMessage(this.itemService.finalizeBasket() ? "Congratulations, you finalized your shopping" : "You cant finalize your basket");
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
}

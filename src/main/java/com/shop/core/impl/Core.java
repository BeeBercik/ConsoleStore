package com.shop.core.impl;

import com.shop.core.ICore;
import com.shop.db.repositories.IConsoleRepository;
import com.shop.db.repositories.IPadRepository;
import com.shop.gui.impl.GUI;
import com.shop.services.impl.ItemService;
import com.shop.services.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Core implements ICore {

    private final GUI gui;
    private final IConsoleRepository consoleRepository;
    private final IPadRepository padRepository;
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
                    this.gui.listAllItems(
                            this.itemService.getAllItems(
                                    this.consoleRepository.getAllConsoles(),
                                    this.padRepository.getAllPads()
                            )
                    );
                    break;
                case "2":
                    String itemId = this.gui.selectItem();
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
}

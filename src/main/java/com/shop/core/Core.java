package com.shop.core;

import com.shop.gui.GUI;
import com.shop.validators.LoginValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class Core {
    private final Scanner scanner = new Scanner(System.in);

    private final GUI gui;
    private final LoginValidator loginValidator;

    public void run() {
        this.gui.welcome();

        int attempts = 0;
        boolean correct = false;
        while(attempts < 3 && !correct) {
            attempts++;
            if(this.loginValidator.checkCredentials(
                    this.gui.askCredentials())) correct = true;
        }

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
}

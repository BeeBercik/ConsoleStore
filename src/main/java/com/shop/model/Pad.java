package com.shop.model;


public class Pad extends Item {
    private int buttons;

    public Pad(int id, String name, int price, int quantity, int buttons) {
        super(id, name, price, quantity);
        this.buttons = buttons;
    }

    @Override
    public String toString() {
        return super.toString() + " | Buttons: " + buttons;
    }
}

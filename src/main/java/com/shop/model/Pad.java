package com.shop.model;


public class Pad extends Product {
    private int buttons;

    public Pad(int id, String name, int price, int buttons) {
        super(id, name, price);
        this.buttons = buttons;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("- ID: ").append(this.id)
                .append(" | Name: ").append(this.name)
                .append(" | Buttons: ").append(this.buttons)
                .append(" | Price: ").append(this.price)
                .toString();
    }
}

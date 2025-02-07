package com.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BasketItem {
    private int id;
    private Item item;
    private int quantity;

    public BasketItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "- ID: " + this.id + " | Name: " + this.item.getName() + " | Price: " + this.item.getPrice() + " | Quantity: " + this.quantity + " | Position cost: " + this.calculatePrice();
    }

    public int calculatePrice() {
        return this.item.getPrice() * this.quantity;
    }
}

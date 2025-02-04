package com.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Item {
    protected int id;
    protected String name;
    protected int price;
    protected int quantity;

    @Override
    public String toString() {
        return new StringBuilder()
                .append("- ID: ").append(this.id)
                .append(" | Name: ").append(this.name)
                .append(" | Price: ").append(this.price)
                .append(" | Count: ").append(this.quantity)
                .toString();
    }
}

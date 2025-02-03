package com.shop.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
//@NoArgsConstructor
public class Product {
    protected int id;
    protected String name;
    protected int price;

    @Override
    public String toString() {
        return new StringBuilder()
                .append("- ID: ").append(this.id)
                .append(" | Name: ").append(this.name)
                .append(" | Price: ").append(this.price)
                .toString();
    }
}

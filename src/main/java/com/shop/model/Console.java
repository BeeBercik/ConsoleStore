package com.shop.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Console {
    private int id;
    private String name;
    private int releaseYear;
    private int price;

    @Override
    public String toString() {
        return new StringBuilder()
                .append("- ID: ").append(this.id)
                .append(" | Name: ").append(this.name)
                .append(" | ReleaseYear: ").append(this.releaseYear)
                .append(" | Price: ").append(this.price)
                .toString();
    }
}

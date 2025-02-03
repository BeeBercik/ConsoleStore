package com.shop.model;


public class Console extends Item {
    private int releaseYear;

    public Console(int id, String name, int price, int releaseYear) {
        super(id, name, price);
        this.releaseYear = releaseYear;
    }

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

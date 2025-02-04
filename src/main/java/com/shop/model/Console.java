package com.shop.model;


public class Console extends Item {
    private int releaseYear;

    public Console(int id, String name, int price, int quantity, int releaseYear) {
        super(id, name, price, quantity);
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return super.toString() + " | ReleaseYear: " + this.releaseYear;
    }
}

package com.shop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbConnect {

    public static final Connection CONNECTION = DbConnect.getConnection();

    public static final String INIT_SCHEMA = """
        SET FOREIGN_KEY_CHECKS=0;
        DROP TABLE IF EXISTS pads;
        DROP TABLE IF EXISTS items;
        DROP TABLE IF EXISTS consoles;
        DROP TABLE IF EXISTS users;
        SET FOREIGN_KEY_CHECKS=1;

        CREATE TABLE IF NOT EXISTS users (
            id INT AUTO_INCREMENT PRIMARY KEY,
            login VARCHAR(20),
            password VARCHAR(60)
        );

        CREATE TABLE IF NOT EXISTS consoles (
            id INT AUTO_INCREMENT PRIMARY KEY,
            releaseYear INT
        );

        CREATE TABLE IF NOT EXISTS pads (
            id INT AUTO_INCREMENT PRIMARY KEY,
            buttons INT
        );

        CREATE TABLE IF NOT EXISTS items (
            id INT AUTO_INCREMENT PRIMARY KEY,
            name VARCHAR(30),
            price INT,
            console_id INT,
            pad_id INT,
            quantity INT,
            CONSTRAINT fg_items_consoles
                FOREIGN KEY (console_id) REFERENCES consoles(id),
            CONSTRAINT fg_items_pads
                FOREIGN KEY (pad_id) REFERENCES pads(id)
        );
        """;

    public static final String INIT_DATA = """
        INSERT INTO users (login, password)
        VALUES ('admin', '$2a$10$yKkCXlF4ss5.aHfiF0AQx.SdMwuObfSoBIbevrLPI0IV7wG.BXJrW');

        INSERT INTO consoles (releaseYear) VALUES (2020);
        INSERT INTO consoles (releaseYear) VALUES (2021);
        INSERT INTO consoles (releaseYear) VALUES (2022);
        INSERT INTO consoles (releaseYear) VALUES (2019);

        INSERT INTO pads (buttons) VALUES (6);
        INSERT INTO pads (buttons) VALUES (4);

        INSERT INTO items (name, price, console_id, pad_id, quantity)
        VALUES ('Xbox Series X', 2500, 3, NULL, 2);

        INSERT INTO items (name, price, console_id, pad_id, quantity)
        VALUES ('PlayStation 5', 2700, 1, NULL, 3);

        INSERT INTO items (name, price, console_id, pad_id, quantity)
        VALUES ('PlayStation Pad', 350, NULL, 1, 6);

        INSERT INTO items (name, price, console_id, pad_id, quantity)
        VALUES ('PlayStation 5 Digital', 2200, 2, NULL, 5);

        INSERT INTO items (name, price, console_id, pad_id, quantity)
        VALUES ('Xbox Pad', 250, NULL, 2, 7);

        INSERT INTO items (name, price, console_id, pad_id, quantity)
        VALUES ('Xbox Series S', 1350, 4, NULL, 1);
        """;

    private static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/console_store?allowMultiQueries=true";
            String user = "root";
            String password = "";

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error during connecting to the db");
            e.printStackTrace();
        }
        return null;
    }

    public static void initSchema() {
        try {
            PreparedStatement ps = DbConnect.CONNECTION.prepareStatement(DbConnect.INIT_SCHEMA);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error during initializing db schema");
            e.printStackTrace();
        }
    }

    public static void initData() {
        try {
            PreparedStatement ps = DbConnect.CONNECTION.prepareStatement(DbConnect.INIT_DATA);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error during initializing db schema");
            e.printStackTrace();
        }
    }
}
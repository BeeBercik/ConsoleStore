package com.shop.db;

import com.shop.gui.IGUI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbConnect {
    public static final Connection CONNECTION = DbConnect.getConnection();

    public static final String INIT_SCHEMA = """
            DROP TABLE IF EXISTS consoles CASCADE;
            DROP TABLE IF EXISTS pads CASCADE;
            DROP TABLE IF EXISTS items CASCADE;            
            DROP TABLE IF EXISTS users CASCADE;      

            create table if not exists users (
                id serial primary key,
                login varchar(20),
                password varchar(60)
            );
            
            create table if not exists consoles (
              id serial primary key,
              releaseYear int
            );
            
            create table if not exists  pads (
              id serial primary key,
              buttons int
            );
            
            create table if not exists  items (
                id serial primary key,
                name varchar(30),
                price int,
                console_id int,
                pad_id int,
                quantity int,
                constraint fg_items_consoles
                foreign key (console_id) references consoles(id),
                constraint fg_items_pads
                foreign key (pad_id) references pads(id)
            );
            """;

    public static final String INIT_DATA = """
        insert into users (login, password) VALUES ('admin', '$2a$10$yKkCXlF4ss5.aHfiF0AQx.SdMwuObfSoBIbevrLPI0IV7wG.BXJrW');

        insert into consoles (releaseYear) VALUES (2020);
        insert into consoles (releaseYear) VALUES (2021);
        insert into consoles (releaseYear) VALUES (2022);
        insert into consoles (releaseYear) VALUES (2019);
        
        insert into pads (buttons) VALUES (6);
        insert into pads (buttons) VALUES (4);
        
        insert into items (name, price, console_id, pad_id, quantity) values ('Xbox Series X', 2500, 3, null, 2);
        insert into items (name, price, console_id, pad_id, quantity) values ('PlayStation 5', 2700, 1, null, 3);
        insert into items (name, price, console_id, pad_id, quantity) values ('PlayStation Pad', 350, null, 1, 6);
        insert into items (name, price, console_id, pad_id, quantity) values ('PlayStation 5 Digital', 2200, 2, null, 5);
        insert into items (name, price, console_id, pad_id, quantity) values ('Xbox Pad', 250, null, 2, 7);
        insert into items (name, price, console_id, pad_id, quantity) values ('Xbox Series S', 1350, 4, null, 1);
    """;

    private static Connection getConnection() {
        try {
            String url = "jdbc:postgresql://localhost:5432/grocery";
            String user = "postgres";
            String password = "";

            return DriverManager.getConnection(url, user, password);
        } catch(SQLException e) {
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

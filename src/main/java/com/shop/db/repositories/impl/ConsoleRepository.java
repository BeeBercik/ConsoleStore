package com.shop.db.repositories.impl;

import com.shop.db.DbConnect;
import com.shop.db.repositories.IConsoleRepository;
import com.shop.gui.IGUI;
import com.shop.model.Console;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsoleRepository implements IConsoleRepository {

    private final IGUI gui;

    private final String GET_ALL_CONSOLES = "SELECT * FROM items LEFT JOIN consoles ON items.console_id = consoles.id where console_id IS NOT NULL";
    private final String GET_BY_ID = "SELECT * FROM consoles WHERE id = ?";

    public List<Console> getAllConsoles() {
        try {
            PreparedStatement ps = DbConnect.CONNECTION.prepareStatement(this.GET_ALL_CONSOLES);
            List<Console> consoles = new ArrayList<>();

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                consoles.add(new Console(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getInt("quantity"),
                    rs.getInt("releaseYear")));
            }

            return consoles;
        } catch(SQLException e) {
            this.gui.showAppMessage("Error in getAllConsoles");
            e.printStackTrace();
        }

        return List.of();
    }
}

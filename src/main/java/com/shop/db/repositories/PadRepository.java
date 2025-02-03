package com.shop.db.repositories;

import com.shop.db.DbConnect;
import com.shop.gui.GUI;
import com.shop.model.Pad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PadRepository {

    private final GUI gui;

    private final String GET_ALL_PADS = "SELECT * FROM items LEFT JOIN pads ON items.pad_id = pads.id where pad_id IS NOT NULL";
    private final String GET_PAD_BY_ID = "SELECT * FROM pads WHERE id = ?";

    public List<Pad> getAllPads() {
        try {
            PreparedStatement ps = DbConnect.CONNECTION.prepareStatement(this.GET_ALL_PADS);
            ResultSet rs = ps.executeQuery();

            List<Pad> pads = new ArrayList<>();
            while(rs.next()) {
                pads.add(new Pad(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("buttons"),
                    rs.getInt("price")));
            }

            return pads;
        } catch (SQLException e) {
            this.gui.showAppMessage("Error in getAllPads");
            e.printStackTrace();
        }

        return List.of();
    }
}

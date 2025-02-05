package com.shop.db.repositories.impl;

import com.shop.db.DbConnect;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class ItemRepository {

    private final String DECREASE_ITEM_QUANTITY = "UPDATE items SET quantity = quantity - 1 WHERE id = ?";

    public boolean decreaseItemQuantity(int id) {
        try {
            PreparedStatement ps = DbConnect.CONNECTION.prepareStatement(this.DECREASE_ITEM_QUANTITY);
            ps.setInt(1, id);
             return ps.executeUpdate() == 1;
        } catch(SQLException e) {
            System.out.println("Error during decreasing item quantity");
            e.printStackTrace();
        }
        return false;
    }
}

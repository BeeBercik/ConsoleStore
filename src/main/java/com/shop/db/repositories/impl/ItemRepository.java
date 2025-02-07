package com.shop.db.repositories.impl;

import com.shop.db.DbConnect;
import com.shop.db.repositories.IItemRepository;
import com.shop.model.Console;
import com.shop.model.Item;
import com.shop.model.Pad;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ItemRepository implements IItemRepository {

    private final String GET_ALL_ITEMS = """
        SELECT * FROM items
        LEFT JOIN consoles on consoles.id = items.console_id
        LEFT JOIN pads on pads.id = items.pad_id
        """;
    private final String DECREASE_ITEM_QUANTITY = "UPDATE items SET quantity = quantity - ? WHERE id = ?";

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = DbConnect.CONNECTION.prepareStatement(this.GET_ALL_ITEMS);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                Integer releaseYear = (Integer) rs.getObject("releaseYear");
                Integer buttons = (Integer) rs.getObject("buttons");

                if(releaseYear != null)
                    items.add(new Console(id, name, price, quantity, releaseYear));
                else if(buttons != null)
                    items.add(new Pad(id, name, price, quantity, buttons));
            }
        } catch (SQLException e) {
            System.out.println("Error during getting all items");
            e.printStackTrace();
        }

        return items;
    }

    public void decreaseItemQuantity(int id, int quantity) {
        try {
            PreparedStatement ps = DbConnect.CONNECTION.prepareStatement(this.DECREASE_ITEM_QUANTITY);
            ps.setInt(1, quantity);
            ps.setInt(2, id);

            ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Error during decreasing item quantity");
            e.printStackTrace();
        }
    }
}

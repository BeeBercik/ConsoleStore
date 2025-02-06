package com.shop.db.repositories.impl;

import com.shop.db.DbConnect;
import com.shop.model.User;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class BasketRepository {

    private final String CREATE_USER_BASKET = "INSERT INTO users_baskets (user_id) VALUES (?)";
    private final String CHECK_USER_BASKET = "select * from users_baskets where user_id = ?";

    public void createUserBasket(User user) {
        try {
            PreparedStatement ps = DbConnect.CONNECTION.prepareStatement(this.CREATE_USER_BASKET);
            ps.setInt(1, user.getId());

            ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Error while creating user basket");
            e.printStackTrace();
        }
    }

    public boolean checkUserBasket(User user) {
        try {
            PreparedStatement ps = DbConnect.CONNECTION.prepareStatement(this.CHECK_USER_BASKET);
            ps.setInt(1, user.getId());

            return ps.executeQuery().next();
        } catch(SQLException e) {
            System.out.println("Error in persist user");
            e.printStackTrace();
        }
        return false;
    }
}

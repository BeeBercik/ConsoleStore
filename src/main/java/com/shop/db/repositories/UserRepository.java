package com.shop.db.repositories;

import com.shop.db.DbConnect;
import com.shop.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepository {
    private final String GET_BY_LOGIN = "select * from users where login = ?";
    private final String PERSIST_USER = "insert into users (login, password) values (?, ?)";

    public Optional<User> getByLogin(String login) {
        try {
            PreparedStatement ps = DbConnect.CONNECTION.prepareStatement(this.GET_BY_LOGIN);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return Optional.of(new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean persist(User user) {
        try {
           if(this.getByLogin(user.getLogin()).isPresent()) {
               return false;
           }

            PreparedStatement ps = DbConnect.CONNECTION.prepareStatement(this.PERSIST_USER);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());

            return ps.executeUpdate() == 1;
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in persist user");
        }
        return false;
    }
}

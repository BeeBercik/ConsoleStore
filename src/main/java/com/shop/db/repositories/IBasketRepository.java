package com.shop.db.repositories;

import com.shop.model.User;

public interface IBasketRepository {
    void createUserBasket(User user);
    boolean checkUserBasket(User user);
}

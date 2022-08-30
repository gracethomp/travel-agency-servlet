package com.travel_agency.service;

import com.travel_agency.entity.User;

public interface UserService extends EntityService<User> {
    User logIn(String login, String password);
    void setDiscount(int id, double discount);
    void setMoney(int id, double money);
}

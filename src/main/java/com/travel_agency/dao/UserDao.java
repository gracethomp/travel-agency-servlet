package com.travel_agency.dao;

import com.travel_agency.entity.Entity;
import com.travel_agency.entity.User;


public interface UserDao <User> extends EntityDao {
    User logIn(String login, String password);
    boolean setMoney(int idUser);
}

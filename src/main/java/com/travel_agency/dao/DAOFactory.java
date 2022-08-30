package com.travel_agency.dao;

import com.travel_agency.dao.impl.*;
import com.travel_agency.entity.Excursion;
import com.travel_agency.entity.Shopping;
import com.travel_agency.entity.User;
import com.travel_agency.entity.Vacation;

public class DAOFactory {
    private final VacationDao<Vacation> vacationDao = new ImplVacationDao();
    private final ExcursionDao<Excursion> excursionDao = new ImplExcursionDao();
    private final ShoppingDao<Shopping> shoppingDao = new ImplShoppingDao();
    private final UserDao<User> userDao = new ImplUserDao();

    private static final DAOFactory instance = new DAOFactory();

    public static DAOFactory getInstance() {
        return instance;
    }

    public VacationDao<Vacation> getVacationDao() {
        return vacationDao;
    }

    public ExcursionDao<Excursion> getExcursionDao() {
        return excursionDao;
    }

    public ShoppingDao<Shopping> getShoppingDao() {
        return shoppingDao;
    }

    public UserDao<User> getUserDao() {
        return userDao;
    }
}

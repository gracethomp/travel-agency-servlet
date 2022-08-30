package com.travel_agency.service.impl;

import com.travel_agency.dao.DAOFactory;
import com.travel_agency.dao.UserDao;
import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.User;
import com.travel_agency.service.UserService;
import com.travel_agency.service.exception.ServiceException;

import java.util.List;

public class ImplUserService implements UserService {
    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final UserDao<User> userDao = daoFactory.getUserDao();

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User logIn(String login, String password) {
        return null;
    }

    @Override
    public void setDiscount(int id, double discount) {

    }

    @Override
    public void setMoney(int id, double money) {

    }
}

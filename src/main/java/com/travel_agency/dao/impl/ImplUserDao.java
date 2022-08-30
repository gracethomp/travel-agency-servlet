package com.travel_agency.dao.impl;

import com.travel_agency.connection_pool.ConnectionPool;
import com.travel_agency.connection_pool.exception.ConnectionPoolException;
import com.travel_agency.dao.UserDao;
import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.Entity;
import com.travel_agency.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplUserDao implements UserDao<User> {
    private static final String SQL_SELECT_ALL_USER = "SELECT id_user, name, surname, email, " +
            "login, password, id_role, roles.title AS title FROM user JOIN roles USING (id_role)";
    private static Logger LOGGER;

    public static void setLOGGER(Logger LOGGER) {
        ImplUserDao.LOGGER = LOGGER;
    }

    @Override
    public boolean create(Entity entity) throws DAOException {
        return false;
    }

    @Override
    public boolean update(Entity entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return false;
    }

    @Override
    public Entity findById(int id) throws DAOException {
        return null;
    }

    @Override
    public List findAll() throws DAOException {
        LOGGER.debug("start find all users");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        ResultSet rs;
        PreparedStatement ps;
        User user;
        List<User> users= new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_ALL_USER);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                users.add(user);
            }
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("find all users exception ", e);
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public User logIn(String login, String password) {
        return null;
    }

    @Override
    public boolean setMoney(int idUser) {
        return false;
    }
}

package com.travel_agency.dao.impl;

import com.travel_agency.connection_pool.ConnectionPool;
import com.travel_agency.connection_pool.exception.ConnectionPoolException;
import com.travel_agency.dao.TourDao;
import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.Tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class ImplTourDao<T extends Tour> implements TourDao<T> {
    private static final String SET_HOT = "UPDATE tours SET isHot = ? WHERE id_tour = ?;";
    @Override
    public boolean create(T entity) throws DAOException {
        return false;
    }

    @Override
    public boolean update(T entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return false;
    }

    @Override
    public T findById(int id) throws DAOException {
        return null;
    }

    @Override
    public List<Tour> findAll() throws DAOException {
        return null;
    }

    @Override
    public boolean setHotTour(int id, boolean isHot) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SET_HOT);
            preparedStatement.setBoolean(1, isHot);
            preparedStatement.setInt(2, id);
            return (preparedStatement.executeUpdate() != 0);
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_CREATING + e, e); //change later
        }
    }
}

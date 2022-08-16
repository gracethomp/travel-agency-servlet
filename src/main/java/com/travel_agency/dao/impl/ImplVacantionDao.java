package com.travel_agency.dao.impl;

import com.travel_agency.connection_pool.ConnectionPool;
import com.travel_agency.connection_pool.exception.ConnectionPoolException;
import com.travel_agency.dao.VacantionDao;
import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.Vacantion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImplVacantionDao implements VacantionDao<Vacantion> {
    private static final String INSERT = "INSERT INTO tours " +
            "(type, price, isHot, dateFrom, dateTo, amountPerson, city, transport, hotel) " +
            "VALUES (?,?,?,?,?,?,?,?,?);";
    private static final String UPDATE = "UPDATE tours SET type=?, price=?, isHot=?, " +
            "dateFrom=?, dateTo=?, amountPerson=?, city=?, transport=?, hotel=? WHERE id_tour=?;";
    private static final String DELETE = "DELETE FROM tours WHERE id_tour = ?;";

    @Override
    public boolean create(Vacantion vacantion) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            setData(preparedStatement, vacantion);
            return (preparedStatement.executeUpdate() != 0);
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_CREATING + e, e);
        }
    }

    @Override
    public boolean update(Vacantion vacantion) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            setData(preparedStatement, vacantion);
            preparedStatement.setInt(10, vacantion.getId());
            return (preparedStatement.executeUpdate() != 0);
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_UPDATING + e, e);
        }
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            return (preparedStatement.executeUpdate() != 0);
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_DELETING + e, e);
        }
    }

    @Override
    public Vacantion findById(int id) throws DAOException {
        return null;
    }

    @Override
    public boolean setHotTour(int id, boolean isHot) throws DAOException {
        return false;
    }

    private void setData(PreparedStatement preparedStatement, Vacantion vacantion) throws SQLException {
        preparedStatement.setDouble(2, vacantion.getPrice());
        preparedStatement.setBoolean(3, vacantion.isHot());
        preparedStatement.setDate(4, Date.valueOf(vacantion.getDateFrom()));
        preparedStatement.setDate(5, Date.valueOf(vacantion.getDateTo()));
        preparedStatement.setInt(6, vacantion.getAmountPerson());
        preparedStatement.setString(7, vacantion.getCity().getName());
        preparedStatement.setString(8, vacantion.getTransportType().getName());
        preparedStatement.setString(9, vacantion.getHotel().getName());
    }
}

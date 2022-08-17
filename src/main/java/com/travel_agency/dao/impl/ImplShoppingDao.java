package com.travel_agency.dao.impl;

import com.travel_agency.connection_pool.ConnectionPool;
import com.travel_agency.connection_pool.exception.ConnectionPoolException;
import com.travel_agency.dao.ShoppingDao;
import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplShoppingDao implements ShoppingDao <Shopping> {
    private static final String INSERT = "INSERT INTO tours " +
            "(type, price, isHot, dateFrom, dateTo, amountPerson, id_city, transport, shops) " +
            "VALUES (?,?,?,?,?,?,?,?,?);";
    private static final String UPDATE = "UPDATE tours SET type=?, price=?, isHot=?, " +
            "dateFrom=?, dateTo=?, amountPerson=?, id_city=?, transport=?, shops=? WHERE id_tour=?;";
    private static final String DELETE = "DELETE FROM tours WHERE id_tour = ?;";
    private static final String SELECT_TOUR_BY_ID = "SELECT id_tour, type, price, isHot, dateFrom, dateTo," +
            "amountPerson, id_city, cities.name AS city_name, cities.id_country, countries.name AS country_name," +
            "transport, shops FROM tours JOIN cities USING (id_city) " +
            "JOIN countries USING (id_country) WHERE id_tour = ?;";
    private static final String SELECT_TOUR_ALL = "SELECT id_tour, type, price, isHot, dateFrom, dateTo, amountPerson, " +
            "id_city, cities.name AS city_name, cities.id_country, countries.name AS country_name, transport, " +
            "shops FROM tours JOIN cities USING (id_city) JOIN countries USING (id_country)";
    private static final String SET_HOT = "UPDATE tours SET isHot = ? WHERE id_tour = ?;";

    @Override
    public boolean create(Shopping shopping) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            setDataPS(preparedStatement, shopping);
            return (preparedStatement.executeUpdate() != 0);
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_CREATING + e, e);
        }
    }

    @Override
    public boolean update(Shopping shopping) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            setDataPS(preparedStatement, shopping);
            return (preparedStatement.executeUpdate() != 0);
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_CREATING + e, e);
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
    public Shopping findById(int id) throws DAOException {
        Shopping shopping = new Shopping();
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOUR_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                setDataList(resultSet, shopping);
            }
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_FINDING_BY_ID + e, e);
        }
        return shopping;
    }

    @Override
    public List<Tour> findAll() throws DAOException {
        List<Tour> shoppings = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOUR_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Shopping shopping = new Shopping();
                setDataList(resultSet, shopping);
                shoppings.add(shopping);
            }
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_FINDING_BY_ID + e, e); //change later
        }
        return shoppings;
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

    private void setDataPS(PreparedStatement preparedStatement, Shopping shopping) throws SQLException {
        preparedStatement.setString(1, shopping.getType().getName());
        preparedStatement.setDouble(2, shopping.getPrice());
        preparedStatement.setBoolean(3, shopping.isHot());
        preparedStatement.setDate(4, Date.valueOf(shopping.getDateFrom()));
        preparedStatement.setDate(5, Date.valueOf(shopping.getDateTo()));
        preparedStatement.setInt(6, shopping.getAmountPerson());
        preparedStatement.setString(7, shopping.getCity().getName());
        preparedStatement.setString(8, shopping.getTransportType().getName());
        preparedStatement.setString(9, shopping.getShops());
    }

    private void setDataList(ResultSet resultSet, Shopping shopping) throws SQLException {
        while (resultSet.next()){
            shopping.setId(resultSet.getInt("id_tour"));
            shopping.setPrice(resultSet.getDouble("price"));
            shopping.setHot(resultSet.getBoolean("isHot"));
            shopping.setDateFrom(resultSet.getDate("dateFrom").toLocalDate());
            shopping.setDateTo(resultSet.getDate("dateTo").toLocalDate());
            shopping.setAmountPerson(resultSet.getInt("amountPerson"));
            shopping.setCity(new City(resultSet.getInt("id_city"),
                    resultSet.getString("city_name"),
                    new Country(resultSet.getInt("id_country"),
                            resultSet.getString("country_name"))));
            shopping.setTransportType(TransportType.valueOf(resultSet.getString("transport")));
            shopping.setShops("shops");
        }
    }
}

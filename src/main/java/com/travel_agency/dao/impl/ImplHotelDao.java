package com.travel_agency.dao.impl;

import com.travel_agency.connection_pool.ConnectionPool;
import com.travel_agency.connection_pool.exception.ConnectionPoolException;
import com.travel_agency.dao.HotelDao;
import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImplHotelDao implements HotelDao {
    private static final String INSERT = "INSERT INTO hotels (name, pricePerDay) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE hotels SET name = ?, pricePerDay = ? WHERE id_hotel = ?";
    private static final String DELETE = "DELETE FROM hotels WHERE id_hotel = ?";
    /*private static final String SELECT_HOTEL_BY_ID = "SELECT id_hotel, hotels.name AS hotel_name, pricePerDay, " +
            "id_city, cities.name AS city_name, id_country, countries.name FROM hotels JOIN cities USING (id_city) " +
            "WHERE id_city = ? JOIN countries USING id_coun";*/
    private static final String SELECT_CITY_BY_ID = "SELECT id_city, cities.name AS city_name, id_country," +
            "countries.name AS country_name FROM cities JOIN countries USING (id_country) WHERE id_city = ?";

    @Override
    public boolean create(Hotel hotel) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setDouble(2, hotel.getPricePerDay());
            return preparedStatement.executeUpdate() != 0;
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_CREATING + e, e);
        }
    }

    @Override
    public boolean update(Hotel hotel) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setDouble(2, hotel.getPricePerDay());
            preparedStatement.setInt(3, hotel.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(DAOException.SQL_UPDATING + e, e);
        }
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(DAOException.SQL_DELETING + e, e);
        }
    }

    @Override
    public Hotel findById(int id) throws DAOException {
        Hotel hotel = new Hotel();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            //PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOTEL_BY_ID);
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(DAOException.SQL_FINDING_BY_ID + e, e);
        }
        return null;
    }
}

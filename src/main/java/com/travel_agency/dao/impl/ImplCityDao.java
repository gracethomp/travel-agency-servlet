package com.travel_agency.dao.impl;

import com.travel_agency.connection_pool.ConnectionPool;
import com.travel_agency.connection_pool.exception.ConnectionPoolException;
import com.travel_agency.dao.CityDao;
import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.City;
import com.travel_agency.entity.Country;
import com.travel_agency.entity.Tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplCityDao implements CityDao {
    private static final String SELECT_ALL_CITIES = "SELECT id_city, cities.name AS city_name, id_country," +
            "countries.name AS country_name FROM cities JOIN countries USING (id_country) ORDER BY cities.name";
    private static final String SELECT_CITY_BY_ID = "SELECT id_city, cities.name AS city_name, id_country," +
            "countries.name AS country_name FROM cities JOIN countries USING (id_country) WHERE id_city = ?";
    private static final String INSERT = "INSERT INTO cities(name, id_country) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE cities SET name = ?, id_country = ? WHERE id_city = ?";
    private static final String DELETE_CITY = "DELETE FROM cities WHERE id_city = ?";
    private static final String SELECT_HOTEL_BY_CITY = "SELECT id_hotel FROM hotels WHERE id_city = ?";
    private static final String DELETE_HOTEL = "DELETE FROM hotels WHERE id_hotel = ?";
    private static final String ID_COUNTRY = "id_country";
    private static final String NAME_COUNTRY = "country_name";
    private static final String ID_CITY = "id_city";
    private static final String NAME_CITY = "city_name";
    private static final String ID_HOTEL = "id_hotel";

    @Override
    public List<City> findAllCities() throws DAOException {
        List<City> cities = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CITIES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Country country = new Country();
                country.setId(resultSet.getInt(ID_COUNTRY));
                country.setName(resultSet.getString(NAME_COUNTRY));
                cities.add(new City(resultSet.getInt(ID_CITY), resultSet.getString(NAME_CITY), country));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(DAOException.SQL_EXCEPTION_FINDING_CITIES, e);
        }
        return cities;
    }

    @Override
    public boolean create(City city) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, city.getName());
            preparedStatement.setInt(2, city.getCountry().getId());
            return (preparedStatement.executeUpdate() != 0);
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_CREATING + e, e);
        }
    }

    @Override
    public boolean update(City city) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, city.getName());
            preparedStatement.setInt(2, city.getCountry().getId());
            preparedStatement.setInt(3, city.getId());
            return (preparedStatement.executeUpdate() != 0);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(DAOException.SQL_UPDATING + e, e);
        }
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CITY);
            PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_HOTEL_BY_CITY);
            PreparedStatement preparedStatement2 = connection.prepareStatement(DELETE_HOTEL);
            preparedStatement1.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                preparedStatement2.setInt(1, resultSet.getInt(ID_HOTEL));
                preparedStatement2.executeUpdate();
            }
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(DAOException.SQL_DELETING + e, e);
        }
    }

    @Override
    public City findById(int id) throws DAOException {
        City city = new City();
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CITY_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                city.setId(resultSet.getInt(ID_CITY));
                city.setName(resultSet.getString(NAME_CITY));
                Country country = new Country();
                country.setId(resultSet.getInt(ID_COUNTRY));
                country.setName(resultSet.getString(NAME_COUNTRY));
                city.setCountry(country);
            }
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(DAOException.SQL_FINDING_BY_ID + e, e);
        }
        return city;
    }

    @Override
    public List<City> findAll() throws DAOException {
        return null;
    }
}

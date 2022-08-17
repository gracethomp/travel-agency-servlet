package com.travel_agency.dao.impl;

import com.travel_agency.connection_pool.ConnectionPool;
import com.travel_agency.connection_pool.exception.ConnectionPoolException;
import com.travel_agency.dao.CountryDao;
import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.Country;
import com.travel_agency.entity.Tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplCountryDao implements CountryDao {
    private static final String SELECT_ALL_COUNTRIES = "SELECT id_country,countries.name AS name_country FROM countries";
    private static final String INSERT = "INSERT INTO countries(name) VALUES(?)";
    private static final String UPDATE = "UPDATE countries SET name=? WHERE id_country = ?";
    private static final String DELETE_COUNTRY = "DELETE FROM countries WHERE id_country = ?";
    private static final String DELETE_CITY = "DELETE FROM cities WHERE id_city = ?";
    private static final String SELECT_CITY_BY_COUNTRY = "SELECT id_city FROM cities WHERE id_country = ?";
    private static final String SELECT_COUNTRY_BY_ID = "SELECT id_country, countries.name AS name_country " +
            "FROM countries WHERE id_country = ?";
    private static final String SELECT_HOTEL_BY_CITY = "SELECT id_hotel FROM hotels WHERE id_city = ?";
    private static final String DELETE_HOTEL = "DELETE FROM hotels WHERE id_hotel = ?";
    private static final String ID_COUNTRY = "id_country";
    private static final String NAME_COUNTRY = "name";
    private static final String ID_CITY = "id_city";
    private static final String ID_HOTEL = "id_hotel";

    @Override
    public List<Country> findAllCountries() throws DAOException {
        List<Country> countries = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COUNTRIES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                countries.add(new Country(resultSet.getInt(ID_COUNTRY), resultSet.getString(NAME_COUNTRY)));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_EXCEPTION_FINDING_COUNTRIES, e);
        }
        return countries;
    }

    @Override
    public boolean create(Country country) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, country.getName());
            return (preparedStatement.executeUpdate() != 0);
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_CREATING, e);
        }
    }

    @Override
    public boolean update(Country country) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, country.getName());
            preparedStatement.setInt(2, country.getId());
            return (preparedStatement.executeUpdate() != 0);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(DAOException.SQL_UPDATING, e);
        }
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COUNTRY);
            PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_CITY_BY_COUNTRY);
            PreparedStatement preparedStatement2 = connection.prepareStatement(DELETE_CITY);
            PreparedStatement preparedStatement3 = connection.prepareStatement(SELECT_HOTEL_BY_CITY);
            PreparedStatement preparedStatement4 = connection.prepareStatement(DELETE_HOTEL);
            preparedStatement1.setInt(1, id);
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()){
                ResultSet resultSet1 = preparedStatement3.executeQuery();
                while (resultSet1.next()) {
                    preparedStatement4.setInt(1, resultSet1.getInt(ID_HOTEL));
                    preparedStatement4.executeUpdate();
                }
                preparedStatement2.setInt(1, resultSet.getInt(ID_CITY));
                preparedStatement2.executeUpdate();
            }
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_DELETING, e);
        }
    }

    @Override
    public Country findById(int id)  throws DAOException {
        Country country = new Country();
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNTRY_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                country.setName(resultSet.getString(NAME_COUNTRY));
                country.setId(resultSet.getInt(ID_COUNTRY));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_FINDING_BY_ID, e);
        }
        return country;
    }

    @Override
    public List<Tour> findAll() throws DAOException {
        return null;
    }
}

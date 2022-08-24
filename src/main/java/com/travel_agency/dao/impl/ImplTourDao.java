package com.travel_agency.dao.impl;

import com.travel_agency.connection_pool.ConnectionPool;
import com.travel_agency.connection_pool.exception.ConnectionPoolException;
import com.travel_agency.dao.TourDao;
import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImplTourDao<T extends Tour> implements TourDao<T> {
    private static final String SET_HOT = "UPDATE tours SET isHot = ? WHERE id_tour = ?;";
    private static final String SELECT_TOUR_ALL = "SELECT id_tour, tours.type, price, isHot, dateFrom, dateTo, " +
            "amountPerson, tours.id_city AS tourcity, cities.name AS city_name, cities.id_country, countries.name AS country_name," +
            "transport, id_hotel, hotels.name AS hotel_name, hotels.pricePerDay FROM tours JOIN cities " +
            "USING (id_city) JOIN hotels USING (id_hotel) JOIN countries USING (id_country)";
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
        List<Tour> tours = new ArrayList<>();
        return tours;
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
    private void setDataList(ResultSet resultSet, Tour tour) throws SQLException {
        while (resultSet.next()){
            tour.setId(resultSet.getInt("id_tour"));
            TourType tourType = new TourType();
            tourType.setId(resultSet.getInt("id_type"));
            tourType.setTitle(resultSet.getString("tour_type"));
            tour.setType(tourType);
            tour.setPrice(resultSet.getDouble("price"));
            tour.setHot(resultSet.getBoolean("isHot"));
            tour.setDateFrom(resultSet.getDate("dateFrom").toLocalDate());
            tour.setDateTo(resultSet.getDate("dateTo").toLocalDate());
            tour.setAmountPerson(resultSet.getInt("amountPerson"));
            tour.setCity(new City(resultSet.getInt("tourcity"),
                    resultSet.getString("city_name"),
                    new Country(resultSet.getInt("id_country"),
                            resultSet.getString("country_name"))));
        }
    }
}

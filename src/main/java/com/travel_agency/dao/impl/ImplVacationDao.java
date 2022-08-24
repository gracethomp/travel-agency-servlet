package com.travel_agency.dao.impl;

import com.travel_agency.connection_pool.ConnectionPool;
import com.travel_agency.connection_pool.exception.ConnectionPoolException;
import com.travel_agency.dao.VacationDao;
import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplVacationDao implements VacationDao<Vacation> {
    private static final String INSERT = "INSERT INTO tours " +
            "(id_type, price, isHot, dateFrom, dateTo, amountPerson, id_city, transport, id_hotel, path, description) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
    private static final String UPDATE = "UPDATE tours SET id_type=?, price=?, isHot=?, " +
            "dateFrom=?, dateTo=?, amountPerson=?, id_city=?, transport=?, id_hotel=?, path=?, description=? " +
            "WHERE id_tour=?;";
    private static final String DELETE = "DELETE FROM tours WHERE id_tour = ?;";
    private static final String SELECT_TOUR_BY_ID = "SELECT id_tour, id_type, tour_types.name AS tour_type, price, isHot, dateFrom, dateTo," +
            "amountPerson, tours.id_city, cities.name AS city_name, cities.id_country, countries.name AS country_name," +
            "transport, id_hotel, hotels.name AS hotel_name, hotels.pricePerDay, path, description FROM tours JOIN hotels " +
            "USING (id_hotel) JOIN cities USING (id_city) JOIN countries USING (id_country) JOIN tour_types " +
            "USING (id_type) WHERE id_tour = ?;";
    private static final String SELECT_TOUR_ALL = "SELECT id_tour, id_type, tour_types.name AS tour_type, price, isHot, dateFrom, dateTo, " +
            "amountPerson, tours.id_city AS tourcity, cities.name AS city_name, cities.id_country, countries.name AS country_name," +
            "transport, id_hotel, hotels.name AS hotel_name, hotels.pricePerDay, path, description FROM tours JOIN cities " +
            "USING (id_city) JOIN hotels USING (id_hotel) JOIN countries USING (id_country) JOIN tour_types USING (id_type)" +
            "WHERE id_type = '1'";
    private static final String SET_HOT = "UPDATE tours SET isHot = ? WHERE id_tour = ?;";

    @Override
    public boolean create(Vacation vacation) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            setDataPS(preparedStatement, vacation);
            return (preparedStatement.executeUpdate() != 0);
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_CREATING + e, e);
        }
    }

    @Override
    public boolean update(Vacation vacation) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            setDataPS(preparedStatement, vacation);
            preparedStatement.setInt(10, vacation.getId());
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
    public Vacation findById(int id) throws DAOException {
        Vacation vacation = new Vacation();
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOUR_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                setDataList(resultSet, vacation);
            }
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_FINDING_BY_ID + e, e);
        }
        return vacation;
    }

    @Override
    public List<Tour> findAll() throws DAOException {
        List<Tour> vacations = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOUR_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Vacation vacation = new Vacation();
                setDataList(resultSet, vacation);
                if (vacation.getId() != 0)
                    vacations.add(vacation);
            }
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_CREATING + e, e); //change later
        }
        return vacations;
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

    private void setDataPS(PreparedStatement preparedStatement, Vacation vacation) throws SQLException {
        preparedStatement.setString(1, vacation.getType().getTitle());
        preparedStatement.setDouble(2, vacation.getPrice());
        preparedStatement.setBoolean(3, vacation.isHot());
        preparedStatement.setDate(4, Date.valueOf(vacation.getDateFrom()));
        preparedStatement.setDate(5, Date.valueOf(vacation.getDateTo()));
        preparedStatement.setInt(6, vacation.getAmountPerson());
        preparedStatement.setString(7, vacation.getCity().getName());
        preparedStatement.setInt(9, vacation.getHotel().getId());
    }

    private void setDataList(ResultSet resultSet, Vacation vacation) throws SQLException {
        vacation.setId(resultSet.getInt("id_tour"));
        TourType tourType = new TourType();
        tourType.setId(resultSet.getInt("id_type"));
        tourType.setTitle(resultSet.getString("tour_type"));
        vacation.setType(tourType);
        vacation.setPrice(resultSet.getDouble("price"));
        vacation.setHot(resultSet.getBoolean("isHot"));
        vacation.setDateFrom(resultSet.getDate("dateFrom").toLocalDate());
        vacation.setDateTo(resultSet.getDate("dateTo").toLocalDate());
        vacation.setAmountPerson(resultSet.getInt("amountPerson"));
        vacation.setCity(new City(resultSet.getInt("tourcity"),
                resultSet.getString("city_name"),
                new Country(resultSet.getInt("id_country"),
                        resultSet.getString("country_name"))));
        vacation.setHotel(new Hotel(resultSet.getInt("id_hotel"),
                resultSet.getString("hotel_name"), resultSet.getDouble("pricePerDay")));
        vacation.setPath(resultSet.getString("path"));
        vacation.setDescription(resultSet.getString("description"));
    }
}

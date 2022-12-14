package com.travel_agency.dao.impl;

import com.travel_agency.connection_pool.ConnectionPool;
import com.travel_agency.connection_pool.exception.ConnectionPoolException;
import com.travel_agency.dao.ExcursionDao;
import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplExcursionDao extends ImplTourDao <Excursion> implements ExcursionDao<Excursion> {
    private static final String INSERT = "INSERT INTO tours " +
            "(id_type, price, isHot, dateFrom, dateTo, amountPerson, id_city, transport, attractions, path, description) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
    private static final String UPDATE = "UPDATE tours SET id_type=?, price=?, isHot=?, " +
            "dateFrom=?, dateTo=?, amountPerson=?, id_city=?, transport=?, attractions=?," +
            " path=?, description=? WHERE id_tour=?;";
    private static final String DELETE = "DELETE FROM tours WHERE id_tour = ?;";
    private static final String SELECT_TOUR_BY_ID = "SELECT id_tour, id_type, price, isHot, dateFrom, dateTo," +
            "amountPerson, id_city, cities.name AS city_name, cities.id_country, countries.name AS country_name," +
            "transport, attractions FROM tours JOIN cities USING (id_city) " +
            "JOIN countries USING (id_country) WHERE id_tour = ?;";
    private static final String SELECT_TOUR_ALL = "SELECT id_tour, id_type, tour_types.name AS tour_type, " +
            "price, isHot, dateFrom, dateTo, amountPerson, " +
            "id_city, cities.name AS city_name, cities.id_country, countries.name AS country_name, transport, " +
            "attractions, path, description FROM tours JOIN cities USING (id_city) JOIN countries USING (id_country)" +
            "JOIN tour_types USING (id_type) WHERE id_type = '2'";

    @Override
    public boolean create(Excursion excursion) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            setDataPS(preparedStatement, excursion);
            return (preparedStatement.executeUpdate() != 0);
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_CREATING + e, e);
        }
    }

    @Override
    public boolean update(Excursion excursion) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            setDataPS(preparedStatement, excursion);
            preparedStatement.setInt(10, excursion.getId());
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
    public Excursion findById(int id) throws DAOException {
        Excursion excursion = new Excursion();
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOUR_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                setDataList(resultSet, excursion);
            }
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_FINDING_BY_ID + e, e);
        }
        return excursion;
    }

    @Override
    public List<Excursion> findAll() throws DAOException {
        List<Excursion> excursions = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try(Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOUR_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Excursion excursion = new Excursion();
                setDataList(resultSet, excursion);
                if (excursion.getId() != 0)
                    excursions.add(excursion);
            }
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        } catch (SQLException e){
            throw new DAOException(DAOException.SQL_FINDING_BY_ID + e, e); //change later
        }
        return excursions;
    }

    private void setDataPS(PreparedStatement preparedStatement, Excursion excursion) throws SQLException {
        preparedStatement.setString(1, excursion.getType().getTitle());
        preparedStatement.setDouble(2, excursion.getPrice());
        preparedStatement.setBoolean(3, excursion.isHot());
        preparedStatement.setDate(4, Date.valueOf(excursion.getDateFrom()));
        preparedStatement.setDate(5, Date.valueOf(excursion.getDateTo()));
        preparedStatement.setInt(6, excursion.getAmountPerson());
        preparedStatement.setString(7, excursion.getCity().getName());
        preparedStatement.setString(9, excursion.getAttractions());
    }

    private void setDataList(ResultSet resultSet, Excursion excursion) throws SQLException {
        excursion.setId(resultSet.getInt("id_tour"));
        TourType tourType = new TourType();
        tourType.setId(resultSet.getInt("id_type"));
        tourType.setTitle(resultSet.getString("tour_type"));
        excursion.setType(tourType);
        excursion.setPrice(resultSet.getDouble("price"));
        excursion.setHot(resultSet.getBoolean("isHot"));
        excursion.setDateFrom(resultSet.getDate("dateFrom").toLocalDate());
        excursion.setDateTo(resultSet.getDate("dateTo").toLocalDate());
        excursion.setAmountPerson(resultSet.getInt("amountPerson"));
        excursion.setCity(new City(resultSet.getInt("id_city"),
                resultSet.getString("city_name"),
                new Country(resultSet.getInt("id_country"),
                        resultSet.getString("country_name"))));
        excursion.setAttractions("attractions");
        excursion.setPath(resultSet.getString("path"));
        excursion.setDescription(resultSet.getString("description"));
    }
}

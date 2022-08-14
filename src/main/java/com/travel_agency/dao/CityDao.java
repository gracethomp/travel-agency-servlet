package com.travel_agency.dao;

import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.City;
import java.util.List;

public interface CityDao extends EntityDao<City> {
    List<City> findAllCities() throws DAOException;
}

package com.travel_agency.dao;

import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.Country;

import java.util.List;

public interface CountryDao extends EntityDao<Country>{
    List<Country> findAllCountries() throws DAOException;
}

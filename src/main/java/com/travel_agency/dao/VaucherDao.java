package com.travel_agency.dao;

import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.Entity;

import java.util.List;

public interface VaucherDao <T extends Entity> extends EntityDao {
    List<T> getVaucherByCountry() throws DAOException;
    List<T> getVaucherByTourType() throws DAOException;
}

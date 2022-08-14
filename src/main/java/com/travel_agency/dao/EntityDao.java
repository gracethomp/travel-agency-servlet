package com.travel_agency.dao;

import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.Entity;

public interface EntityDao <T extends Entity> {
    boolean create(T entity) throws DAOException;
    boolean update(T entity) throws DAOException;
    boolean delete(int id) throws DAOException;
    T findById(int id) throws DAOException;
}

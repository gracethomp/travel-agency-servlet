package com.travel_agency.service;

import com.travel_agency.entity.Entity;
import com.travel_agency.service.exception.ServiceException;

import java.util.List;

public interface EntityService<T extends Entity> {
    boolean create(T entity);
    boolean update(T entity);
    void delete(int id);
    T findById(int id);
    List<T> findAll() throws ServiceException;
}

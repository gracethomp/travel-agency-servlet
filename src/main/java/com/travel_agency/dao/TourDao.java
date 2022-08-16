package com.travel_agency.dao;

import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.Entity;
import com.travel_agency.entity.Tour;

public interface TourDao<T extends Tour> extends EntityDao<T> {
    boolean setHotTour(int id, boolean isHot) throws DAOException;
}

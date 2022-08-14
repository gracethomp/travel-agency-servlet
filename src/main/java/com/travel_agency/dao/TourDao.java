package com.travel_agency.dao;

import com.travel_agency.dao.exception.DAOException;

public interface TourDao extends EntityDao {
    boolean setHotTour(int id, boolean isHot) throws DAOException;
}

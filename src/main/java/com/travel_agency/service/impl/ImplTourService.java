package com.travel_agency.service.impl;

import com.travel_agency.entity.Country;
import com.travel_agency.entity.Tour;
import com.travel_agency.entity.TourType;
import com.travel_agency.service.TourService;

import java.util.List;

public class ImplTourService implements TourService {
    @Override
    public boolean create(Tour entity) {
        return false;
    }

    @Override
    public boolean update(Tour entity) {
        return false;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Tour findById(int id) {
        return null;
    }

    @Override
    public List<Tour> findAll() {
        return null;
    }

    @Override
    public List<Tour> getTourByCountry(Country country) {
        return null;
    }

    @Override
    public List<Tour> getTourByType(TourType type) {
        return null;
    }

    @Override
    public boolean setHotTour(int id, boolean isHot) {
        return false;
    }
}

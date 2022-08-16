package com.travel_agency.service;

import com.travel_agency.entity.Country;
import com.travel_agency.entity.Tour;
import com.travel_agency.entity.TourType;

import java.util.List;

public interface TourService extends EntityService <Tour> {
    List<Tour> getTourByCountry(Country country);
    List<Tour> getTourByType(TourType type);
    boolean setHotTour(int id, boolean isHot);
}

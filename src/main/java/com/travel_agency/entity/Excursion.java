package com.travel_agency.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Excursion extends Tour {
    private String attractions;

    public Excursion() {
    }

    public Excursion(int id, TourType type, double price, boolean isHot, String attractions,
                     City city, LocalDate dateFrom, LocalDate dateTo, Tour tour,
                     int amountPerson, String path, String description){
        super(id, type, price, isHot, city, dateFrom, dateTo, tour, amountPerson, path, description);
        this.attractions = attractions;
    }

    public void setAttractions(String attractions) {
        this.attractions = attractions;
    }

    public String getAttractions() {
        return attractions;
    }

    @Override
    public String toString() {
        return " Excursion{" +
                "attractions='" + attractions + '\'' +
                "} " + super.toString();
    }
}

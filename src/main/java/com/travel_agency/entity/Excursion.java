package com.travel_agency.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Excursion extends Tour {
    private String attractions;

    public Excursion() {
    }

    public Excursion(int id, TourType type, double price, boolean isHot, String attractions,
                     City city, LocalDate dateFrom, LocalDate dateTo,
                     Tour tour, TransportType transportType, int amountPerson){
        super(id, type, price, isHot, city, dateFrom, dateTo, tour, transportType, amountPerson);
        this.attractions = attractions;
    }

    public void setAttractions(String attractions) {
        this.attractions = attractions;
    }

    public String getAttractions() {
        return attractions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Excursion excursion = (Excursion) o;
        return Objects.equals(attractions, excursion.attractions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), attractions);
    }

    @Override
    public String toString() {
        return " Excursion{" +
                "attractions='" + attractions + '\'' +
                "} " + super.toString();
    }
}

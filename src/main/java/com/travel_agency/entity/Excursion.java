package com.travel_agency.entity;

import java.util.Objects;

public class Excursion extends Tour {
    private String attractions;

    public Excursion() {
    }

    public Excursion(int id, TourType type, double price, boolean isHot, String attractions){
        super(id, type, price, isHot);
        this.attractions = attractions;
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

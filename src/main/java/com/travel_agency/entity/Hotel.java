package com.travel_agency.entity;

import java.util.Objects;

public class Hotel extends Entity {
    private String name;
    private double pricePerDay;

    public Hotel() {
    }

    public Hotel(int id, String name, double pricePerDay) {
        super(id);
        this.name = name;
        this.pricePerDay = pricePerDay;
    }

    public String getName() {
        return name;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Hotel hotel = (Hotel) o;
        return Double.compare(hotel.pricePerDay, pricePerDay) == 0 && Objects.equals(name, hotel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, pricePerDay);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", pricePerDay=" + pricePerDay +
                '}';
    }
}

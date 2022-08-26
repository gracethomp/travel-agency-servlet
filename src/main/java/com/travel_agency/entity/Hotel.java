package com.travel_agency.entity;

import java.util.Objects;

public class Hotel extends Entity {
    private String name;
    private double pricePerDay;
    private int type;
    private City city;

    public Hotel() {
    }

    public Hotel(int id, String name, double pricePerDay, int type, City city) {
        super(id);
        this.name = name;
        this.pricePerDay = pricePerDay;
        this.type = type;
        this.city = city;
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

    public City getCity() {
        return city;
    }

    public int getType() {
        return type;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setType(int type) {
        this.type = type;
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

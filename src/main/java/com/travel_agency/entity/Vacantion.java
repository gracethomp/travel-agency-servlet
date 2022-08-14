package com.travel_agency.entity;

import java.util.Objects;

public class Vacantion extends Tour {
    private Hotel hotel;

    public Vacantion() {
    }

    public Vacantion(int id, TourType type, double price, boolean isHot, Hotel hotel){
        super(id, type, price, isHot);
        this.hotel = hotel;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Vacantion vacantion = (Vacantion) o;
        return Objects.equals(hotel, vacantion.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hotel);
    }

    @Override
    public String toString() {
        return "Vacantion{" +
                "hotel=" + hotel +
                "} " + super.toString();
    }
}

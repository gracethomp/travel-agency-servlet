package com.travel_agency.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Vacation extends Tour {
    private Hotel hotel;

    public Vacation() {
    }

    public Vacation(int id, TourType type, double price, boolean isHot, Hotel hotel,
                    City city, LocalDate dateFrom, LocalDate dateTo, Tour tour,
                    int amountPerson, String path, String description){
        super(id, type, price, isHot, city, dateFrom, dateTo, tour, amountPerson, path, description);
        this.hotel = hotel;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "hotel=" + hotel +
                "} " + super.toString();
    }
}

package com.travel_agency.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Vacation extends Tour {
    private Hotel hotel;

    public Vacation() {
    }

    public Vacation(int id, TourType type, double price, boolean isHot, Hotel hotel,
                    City city, LocalDate dateFrom, LocalDate dateTo,
                    Tour tour, TransportType transportType, int amountPerson){
        super(id, type, price, isHot, city, dateFrom, dateTo, tour, transportType, amountPerson);
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
        Vacation vacation = (Vacation) o;
        return Objects.equals(hotel, vacation.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hotel);
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "hotel=" + hotel +
                "} " + super.toString();
    }
}

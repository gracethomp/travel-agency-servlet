package com.travel_agency.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Shopping extends Tour {
    private String shops;

    public Shopping() {
    }

    public Shopping(int id, TourType type, double price, boolean isHot, String shops,
                    City city, LocalDate dateFrom, LocalDate dateTo, Tour tour,
                    int amountPerson, String path, String description){
        super(id, type, price, isHot, city, dateFrom, dateTo, tour, amountPerson, path, description);
        this.shops = shops;
    }

    public String getShops() {
        return shops;
    }

    public void setShops(String shops) {
        this.shops = shops;
    }

    @Override
    public String toString() {
        return "Shopping{" +
                "shops='" + shops + '\'' +
                "} " + super.toString();
    }
}

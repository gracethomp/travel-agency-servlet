package com.travel_agency.entity;

import java.util.Objects;

public class Shopping extends Tour {
    private String shops;

    public Shopping() {
    }

    public Shopping(int id, TourType type, double price, boolean isHot, String shops){
        super(id, type, price, isHot);
        this.shops = shops;
    }

    public String getShops() {
        return shops;
    }

    public void setShops(String shops) {
        this.shops = shops;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Shopping shopping = (Shopping) o;
        return Objects.equals(shops, shopping.shops);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shops);
    }

    @Override
    public String toString() {
        return "Shopping{" +
                "shops='" + shops + '\'' +
                "} " + super.toString();
    }
}

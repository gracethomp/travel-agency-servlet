package com.travel_agency.entity;

import java.util.Objects;

public abstract class Tour extends Entity {
    private TourType type;
    private double price;
    private boolean isHot;

    public Tour() {
    }

    public Tour(int id, TourType type, double price, boolean isHot) {
        super(id);
        this.type = type;
        this.price = price;
        this.isHot = isHot;
    }

    public TourType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(TourType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Tour tour = (Tour) o;
        return Double.compare(tour.price, price) == 0 && isHot == tour.isHot && Objects.equals(type, tour.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, price, isHot);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", isHot=" + isHot +
                '}';
    }
}

package com.travel_agency.entity;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Tour extends Entity {
    private TourType type;
    private double price;
    private boolean isHot;
    private City city;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Tour tour;
    private TransportType transportType;
    private int amountPerson;

    public Tour() {
    }

    public Tour(int id, TourType type, double price, boolean isHot, City city, LocalDate dateFrom, LocalDate dateTo,
                Tour tour, TransportType transportType, int amountPerson) {
        super(id);
        this.type = type;
        this.price = price;
        this.isHot = isHot;
        this.city = city;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.tour = tour;
        this.transportType = transportType;
        this.amountPerson = amountPerson;
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

    public Tour getTour() {
        return tour;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public City getCity() {
        return city;
    }

    public int getAmountPerson() {
        return amountPerson;
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

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }


    public void setAmountPerson(int amountPerson) {
        this.amountPerson = amountPerson;
    }

    public void setCity(City city) {
        this.city = city;
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

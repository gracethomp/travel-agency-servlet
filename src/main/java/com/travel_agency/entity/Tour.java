package com.travel_agency.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Tour extends Entity {
    private TourType type;
    private double price;
    private boolean hot;
    private City city;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Tour tour;
    private int amountPerson;
    private String path;
    private String description;

    public Tour() {
    }

    public Tour(int id, TourType type, double price, boolean hot, City city, LocalDate dateFrom, LocalDate dateTo,
                Tour tour, int amountPerson, String path, String description) {
        super(id);
        this.type = type;
        this.price = price;
        this.hot = hot;
        this.city = city;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.tour = tour;
        this.amountPerson = amountPerson;
        this.path = path;
        this.description = description;
    }

    public TourType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isHot() {
        return hot;
    }

    public Tour getTour() {
        return tour;
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

    public String getDescription() {
        return description;
    }

    public String getPath() {
        return path;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
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

    public void setAmountPerson(int amountPerson) {
        this.amountPerson = amountPerson;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", isHot=" + hot +
                '}';
    }
}

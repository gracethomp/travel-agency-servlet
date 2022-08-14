package com.travel_agency.entity;

import java.time.*;
import java.util.Objects;

public class Vaucher extends Entity{
    private String country;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Tour tour;
    private TransportType transportType;

    public Vaucher(){
    }

    public Vaucher(int id, String country, LocalDate dateFrom, LocalDate dateTo,
                   Tour tour, TransportType transportType) {
        super(id);
        this.country = country;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.tour = tour;
        this.transportType = transportType;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public String getCountry() {
        return country;
    }

    public Tour getTour() {
        return tour;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Vaucher vaucher = (Vaucher) o;
        return Objects.equals(country, vaucher.country) && Objects.equals(dateFrom, vaucher.dateFrom)
                && Objects.equals(dateTo, vaucher.dateTo) && Objects.equals(tour, vaucher.tour)
                && transportType == vaucher.transportType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), country, dateFrom, dateTo, tour, transportType);
    }

    @Override
    public String toString() {
        return "Vaucher{" +
                "country='" + country + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", tour=" + tour +
                ", transportType=" + transportType +
                '}';
    }
}

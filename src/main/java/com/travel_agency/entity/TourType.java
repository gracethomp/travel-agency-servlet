package com.travel_agency.entity;

public enum TourType {

    VACATIONS("vacations"),
    TRIPS("trips"),
    SHOPPING("shopping");

    private String name;

    TourType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

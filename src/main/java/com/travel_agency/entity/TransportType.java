package com.travel_agency.entity;

public enum TransportType {
    PLAIN("plain"),
    TRAIN("train"),
    BUS("bus");

    private String name;

    TransportType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

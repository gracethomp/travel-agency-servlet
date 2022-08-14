package com.travel_agency.entity;

public enum TransportType {
    PLAIN(1), TRAIN(2), BUS(3);

    private int id;

    TransportType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package com.travel_agency.entity;

public enum RoleType {
    ADMIN(1), USER(2);
    private int id;

    RoleType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

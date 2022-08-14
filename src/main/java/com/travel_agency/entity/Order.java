package com.travel_agency.entity;

public class Order extends Entity {
    private User user;
    private double totalPrice;

    public Order() {
    }

    public Order(int id, User user, double totalPrice) {
        super(id);
        this.user = user;
        this.totalPrice = totalPrice;
    }

}

package com.travel_agency.entity;

public class Order extends Entity {
    private User user;
    private double totalPrice;
    private Tour tour;

    public Order() {
    }

    public Order(int id, User user, double totalPrice, Tour tour) {
        super(id);
        this.user = user;
        this.totalPrice = totalPrice;
        this.tour = tour;
    }

    public Tour getTour() {
        return tour;
    }

    public User getUser() {
        return user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

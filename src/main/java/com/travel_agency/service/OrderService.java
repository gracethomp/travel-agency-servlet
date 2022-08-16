package com.travel_agency.service;

import com.travel_agency.entity.Order;

import java.util.List;

public interface OrderService extends EntityService <Order>{
    boolean cancel(Order order);
    List<Order> getByUserId(int id);
}

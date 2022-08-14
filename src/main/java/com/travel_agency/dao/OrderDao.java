package com.travel_agency.dao;

import com.travel_agency.entity.Entity;
import com.travel_agency.entity.Order;

import java.util.List;

public interface OrderDao <T extends Entity> extends EntityDao {
    boolean cancel(Order order);
    List<T> findByIdUsers(int userId);
}

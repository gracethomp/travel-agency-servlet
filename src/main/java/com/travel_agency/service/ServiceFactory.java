package com.travel_agency.service;

import com.travel_agency.service.impl.ImplUserService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final UserService USER_SERVICE = new ImplUserService();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return USER_SERVICE;
    }
}

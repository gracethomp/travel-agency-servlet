package com.travel_agency.validator;

import com.travel_agency.entity.User;
import com.travel_agency.service.ServiceFactory;
import com.travel_agency.service.UserService;
import com.travel_agency.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class Validator {
    private static final String NAME_AND_SURNAME_PATTERN = "[А-Яа-яA-Za-z]{1,15}";
    private static final String LOGIN_AND_PASSWORD_PATTERN = "[A-Za-z\\d]{1,10}";
    private static final String EMAIL_PATTERN = "([A-Za-z\\d]+)@([A-Za-z]+)\\.[A-Za-z]{1,5}";
    private static final String DATE_PATTERN = "[2][\\d]{3}\\-[0-1][0-9]\\-[0-3][0-9]";
    private static final Logger LOGGER;
    static {
        LOGGER = Logger.getRootLogger();
    }
    private static final Validator instance = new Validator();

    public Validator() {
    }

    public static Validator getInstance() {
        return instance;
    }


    public boolean validateNameOrSurname (String string) {
        return string.matches(NAME_AND_SURNAME_PATTERN);
    }
    public boolean validateLoginOrPassword (String string) {
        return string.matches(LOGIN_AND_PASSWORD_PATTERN);
    }

    public boolean validateEmail (String string) {
        return string.matches(EMAIL_PATTERN);
    }
    public boolean validateUniqueLogin(String login) throws ServiceException {
        LOGGER.debug("start validateUniqueLogin login: " + login);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        List<User> users = userService.findAll();
        int count = 0;
        for (User entity : users) {
            if (entity.getLogin().equals(login)) {
                count++;
            }
        }
        LOGGER.debug("finish validateUniqueLogin, find users: " + count);
        return count == 0;
    }
}

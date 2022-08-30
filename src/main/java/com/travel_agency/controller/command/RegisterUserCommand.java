package com.travel_agency.controller.command;

import com.travel_agency.entity.User;
import com.travel_agency.service.ServiceFactory;
import com.travel_agency.service.UserService;
import com.travel_agency.service.exception.ServiceException;
import com.travel_agency.validator.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegisterUserCommand implements Command{
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }
    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start RegisterUserCommand");

        User user = null;
        String page;
        if (validate(request)) {
            LOGGER.debug(request.getParameter("roleUser"));
            Validator validator = Validator.getInstance();

            try {
                if (validator.validateUniqueLogin(request.getParameter("login"))) {
                    user = new User();
                    user.setEmail(request.getParameter("email"));
                    user.setLogin(request.getParameter("login"));
                    user.setPassword(request.getParameter("password"));

                    String role = request.getParameter("userRole");

                    ServiceFactory factory = ServiceFactory.getInstance();
                    UserService userService = factory.getUserService();

                    userService.create(user);
                    request.setAttribute("user", user);
                    request.setAttribute("name", user.getName());
                    request.getSession().setAttribute("user", user);
                    page = "WEB-INF/jsp/result_register.jsp";
                } else {
                    request.setAttribute("error", "Login is not unique. Please try again.");
                    page = "WEB-INF/jsp/error.jsp";
                }
            } catch (ServiceException e) {
                LOGGER.error("RegisterUserCommand error.", e);
                page = "WEB-INF/jsp/error.jsp";
            }
        } else {
            request.setAttribute("error", "The data entered is not correct. Please try again.");
            page = "WEB-INF/jsp/error.jsp";
        }
        LOGGER.debug("finish RegisterUserCommand" + user);
        return page;
    }
    private boolean validate(HttpServletRequest request) {
        LOGGER.debug("start boolean validate");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        Double money = Double.parseDouble(request.getParameter("money"));
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Validator validator = Validator.getInstance();

        boolean result = validator.validateNameOrSurname(name) &&
                validator.validateNameOrSurname(surname) &&
                validator.validateEmail(email) &&
                validator.validateLoginOrPassword(login) &&
                validator.validateLoginOrPassword(password);

        LOGGER.debug("finish boolean validate with: " + result);

        return result;
    }
}

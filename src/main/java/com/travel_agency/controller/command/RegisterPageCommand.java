package com.travel_agency.controller.command;

import javax.servlet.http.HttpServletRequest;

public class RegisterPageCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return "WEB-INF/jsp/register.jsp";
    }
}

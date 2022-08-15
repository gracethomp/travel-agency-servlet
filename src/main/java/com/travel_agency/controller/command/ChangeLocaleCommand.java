package com.travel_agency.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;

public class ChangeLocaleCommand implements Command{
    private static final String LOCALIZATION = "localization";
    @Override
    public String execute(HttpServletRequest request) {
        String localization = request.getParameter(LOCALIZATION);
        request.getSession().setAttribute(LOCALIZATION, localization);
        Config.set(request.getSession(), Config.FMT_LOCALE, localization);
        return "main.jsp";
    }
}

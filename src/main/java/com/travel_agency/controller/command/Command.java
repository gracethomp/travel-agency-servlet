package com.travel_agency.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    public String execute(HttpServletRequest request);
}
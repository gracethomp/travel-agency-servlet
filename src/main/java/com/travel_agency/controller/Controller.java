package com.travel_agency.controller;

import com.travel_agency.controller.command.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet (name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {
    private static final String COMMAND = "command";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        String commandName = req.getParameter(COMMAND);
        CommandInitializer commandInitializer = new CommandInitializer();
        Command command = commandInitializer.getCommand(commandName);
        String page = command.execute(req);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    private static class CommandInitializer {
        private final Map<Commands, Command> commands = new HashMap<>();
        private CommandInitializer() {
            commands.put(Commands.CHANGE_LOCALE, new ChangeLocaleCommand());
            commands.put(Commands.VIEW_ALL_VAUCHERS, new ViewAllVauchers());
            commands.put(Commands.VIEW_TOURS_BY_TYPE, new ViewToursByType());
            commands.put(Commands.ORDER_BY_PRICE, new OrderByPrice());
            commands.put(Commands.ORDER_BY_NUMBER_OF_PERSON, new OrderByNumberOfPerson());
            commands.put(Commands.ORDER_BY_HOTEL, new OrderByHotel());
        }
        public Command getCommand(String name){
            return commands.get(Commands.valueOf(name.toUpperCase()));
        }
    }
}

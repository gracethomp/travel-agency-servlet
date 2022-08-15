package com.travel_agency.controller;

import com.travel_agency.controller.command.ChangeLocaleCommand;
import com.travel_agency.controller.command.Command;
import com.travel_agency.controller.command.Commands;

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
        }
        public Command getCommand(String name){
            System.out.println(commands.get(Commands.CHANGE_LOCALE));
            return commands.get(Commands.valueOf(name.toUpperCase()));
        }
    }
}
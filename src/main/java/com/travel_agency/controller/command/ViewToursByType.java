package com.travel_agency.controller.command;

import com.travel_agency.dao.DAOFactory;
import com.travel_agency.dao.TourDao;
import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.Excursion;
import com.travel_agency.entity.Shopping;
import com.travel_agency.entity.Tour;
import com.travel_agency.entity.Vacation;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

public class ViewToursByType implements Command{
    private static final String TYPE = "type";
    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final TourDao<Excursion> excursionTourDao = daoFactory.getExcursionDao();
    private final TourDao<Vacation> vacationTourDao = daoFactory.getVacationDao();
    private final TourDao<Shopping> shoppingTourDao = daoFactory.getShoppingDao();
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String type = request.getParameter(TYPE);
        try {
            HashSet<Tour> tours = new HashSet<>();
            switch (type) {
                case ("1"): tours.addAll(vacationTourDao.findAll()); break;
                case ("2"): tours.addAll(excursionTourDao.findAll()); break;
                case ("3"): tours.addAll(shoppingTourDao.findAll()); break;
            }
            System.out.println(tours);
            if (!tours.isEmpty())
                request.setAttribute("tours", tours);
            else
                request.setAttribute("error", "Tours not found");
            page = "WEB-INF/jsp/view_all_tours.jsp";
        } catch (DAOException e) {
            e.printStackTrace();
            page = "WEB-INF/jsp/error.jsp";
        }
        return page;
    }
}

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

public class ViewAllVauchers implements Command{
    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final TourDao<Excursion> excursionTourDao = daoFactory.getExcursionDao();
    private final TourDao<Vacation> vacationTourDao = daoFactory.getVacationDao();
    private final TourDao<Shopping> shoppingTourDao = daoFactory.getShoppingDao();
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        try {
            HashSet<Tour> tours = new HashSet<>();
            tours.addAll(excursionTourDao.findAll());
            tours.addAll(vacationTourDao.findAll());
            tours.addAll(shoppingTourDao.findAll());
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

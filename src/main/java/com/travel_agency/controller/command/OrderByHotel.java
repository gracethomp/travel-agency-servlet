package com.travel_agency.controller.command;

import com.travel_agency.dao.DAOFactory;
import com.travel_agency.dao.TourDao;
import com.travel_agency.dao.exception.DAOException;
import com.travel_agency.entity.Excursion;
import com.travel_agency.entity.Shopping;
import com.travel_agency.entity.Tour;
import com.travel_agency.entity.Vacation;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class OrderByHotel implements Command{
    private static final String TYPE = "type";
    private static final String SORT = "sort";
    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final TourDao<Vacation> vacationTourDao = daoFactory.getVacationDao();
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String type = request.getParameter(TYPE);
        String sort = request.getParameter(SORT);
        Comparator<Vacation> comparator = Comparator.comparing(obj -> obj.getHotel().getType());
        System.out.println(request.getAttribute("tours"));
        try {
            List<Vacation> tours;
            tours = vacationTourDao.findAll();
            if(Objects.equals(sort, "desc")) {
                tours.sort(comparator.reversed());
            }
            else
                tours.sort(comparator);
            if (!tours.isEmpty()) {
                request.setAttribute("tours", tours);
                request.setAttribute("tourType", type);
            }
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

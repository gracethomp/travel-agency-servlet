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

public class OrderByNumberOfPerson implements Command{
    private static final String TYPE = "type";
    private static final String SORT = "sort";
    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final TourDao<Excursion> excursionTourDao = daoFactory.getExcursionDao();
    private final TourDao<Vacation> vacationTourDao = daoFactory.getVacationDao();
    private final TourDao<Shopping> shoppingTourDao = daoFactory.getShoppingDao();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String type = request.getParameter(TYPE);
        String sort = request.getParameter(SORT);
        Comparator<Tour> comparator = Comparator.comparing(Tour::getAmountPerson);
        System.out.println(request.getAttribute("tours"));
        List<Tour> tourList;
        try {
            HashSet<Tour> tours = new HashSet<>();
            switch (type) {
                case ("all"): {
                    tours.addAll(shoppingTourDao.findAll());
                    tours.addAll(excursionTourDao.findAll());
                    tours.addAll(vacationTourDao.findAll());
                    tourList = new ArrayList<>(tours);
                    if(Objects.equals(sort, "desc"))
                        tourList.sort(comparator.reversed());
                    else
                        tourList.sort(comparator);
                    break;
                }
                case ("1"): {
                    tours.addAll(vacationTourDao.findAll());
                    tourList = new ArrayList<>(tours);
                    if(Objects.equals(sort, "desc"))
                        tourList.sort(comparator.reversed());
                    else
                        tourList.sort(comparator);
                    break;
                }
                case ("2"): {
                    tours.addAll(excursionTourDao.findAll());
                    tourList = new ArrayList<>(tours);
                    if(Objects.equals(sort, "desc"))
                        tourList.sort(comparator.reversed());
                    else
                        tourList.sort(comparator);
                    break;
                }
                case ("3"): {
                    tours.addAll(shoppingTourDao.findAll());
                    tourList = new ArrayList<>(tours);
                    if(Objects.equals(sort, "desc"))
                        tourList.sort(comparator.reversed());
                    else
                        tourList.sort(comparator);
                    break;
                }
                default: tourList = new ArrayList<>(tours);
            }
            System.out.println(tourList);
            if (!tourList.isEmpty()) {
                request.setAttribute("tours", tourList);
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

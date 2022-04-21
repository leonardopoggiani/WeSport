package it.unipi.dsmt.servlets;


import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import it.unipi.dsmt.dto.FieldBookingDTO;
import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.FieldBookingRemote;
import it.unipi.dsmt.utils.Utils;


import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BookingServlet", value = "/booking")
public class BookingServlet extends HttpServlet {

    @EJB
    private FieldBookingRemote fieldBookingRemote;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    HttpSession session = request.getSession();
    UserDTO user = (UserDTO) session.getAttribute("logged_user");

    List<FieldBookingDTO> bookings = new ArrayList<>();

    long miliseconds = System.currentTimeMillis();
    Calendar date = Calendar.getInstance();

    String sport = request.getParameter("sports");
    //System.out.println("Sport: " + sport);
    if (sport == null) sport = "tennis";
    String month = request.getParameter("month");
    int monthNumber = (LocalDate.now().getMonthValue())-1;
    if (month != null){
      monthNumber = Utils.getCalendarMonth(month);
    }
    //int calendarMonth =utils.getCalendarMonth(month);
    String year = request.getParameter("year");
    if (year == null) year = Integer.toString(Year.now().getValue());
    String day = request.getParameter("day");
    int dayNum;
    String targetJSP = "/pages/jsp/booking.jsp";
    date.set(Calendar.MONTH, monthNumber);
    date.set(Calendar.YEAR, Integer.parseInt(year));
    if(day != null){
      dayNum = Integer.parseInt(day);
      FieldBookingDTO fieldBooking = new FieldBookingDTO();
      fieldBooking.setBooker(user.getId());
      fieldBooking.setSport(sport);
      date.set(Calendar.DATE, dayNum);
      fieldBooking.setDay(date.getTime());
      session.setAttribute("fieldBooking", fieldBooking);
      response.sendRedirect(request.getContextPath()+"/timeslot");
    }
    else{
      dayNum = date.get(Calendar.DATE);
      bookings = fieldBookingRemote.displayBookingForSport(sport);
      boolean[] freeDays = fieldBookingRemote.displayBusyDaysForMonth(sport, date.getTime());
      bookings = fieldBookingRemote.displayBookingForSport(sport);

      request.setAttribute("bookings", bookings);
      request.setAttribute("freeDays", freeDays);
      request.setAttribute("monthNumber", monthNumber);
      request.setAttribute("year", year);

      targetJSP = "/pages/jsp/booking.jsp";
      RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetJSP);
      requestDispatcher.forward(request,response);

    }


  }
}

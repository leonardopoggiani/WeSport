package it.unipi.dsmt.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
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
    private FieldBookingRemote fieldBookingEJB;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    HttpSession session = request.getSession();
    UserDTO user = (UserDTO) session.getAttribute("logged_user");

    List<FieldBookingDTO> bookings = new ArrayList<>();
    System.out.println("[LOG] username: " + user.getUsername());

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
    System.out.println("Mese: " + monthNumber);
    //int calendarMonth =utils.getCalendarMonth(month);
    String year = request.getParameter("year");
    if (year == null) year = Integer.toString(Year.now().getValue());
    System.out.println("Qui:"+ year);
    String day = request.getParameter("day");
    System.out.println(day);
    int dayNum;
    String targetJSP = "/pages/jsp/booking.jsp";
    date.set(Calendar.MONTH, monthNumber);
    System.out.println("CalendarMonth:"+ Calendar.MONTH);
    date.set(Calendar.YEAR, Integer.parseInt(year));
    if(day != null){
      dayNum = Integer.parseInt(day);
      FieldBookingDTO fieldBooking = new FieldBookingDTO();
      fieldBooking.setBooker(user.getId());
      fieldBooking.setSport(sport);
      date.set(Calendar.DATE, dayNum);
      fieldBooking.setDay(date.getTime());
      /*request.setAttribute("sports", sport);
      request.setAttribute("month", monthNumber);
      request.setAttribute("year",year);
      request.setAttribute("day",dayNum);*/
      /*System.out.println("Nel servlet:"+ sport+ monthNumber+year+dayNum);
      System.out.println(request.getContextPath());*/
      session.setAttribute("fieldBooking", fieldBooking);
      targetJSP = "/pages/jsp/booktimeslot.jsp";
      //System.out.println("Day ricevuto");
      /*RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetJSP);
      requestDispatcher.forward(request,response);*/
      response.sendRedirect(request.getContextPath()+"/timeslot");
    }
    else{
      dayNum = date.get(Calendar.DATE);
      bookings = fieldBookingEJB.displayBookingForSport(sport);
      boolean[] freeDays = fieldBookingEJB.displayBusyDaysForMonth(sport, date.getTime());
      System.out.println("[LOG] Di nuovo date: " + date.getTime() );
      for(int i = 0; i < freeDays.length; i++) {
        System.out.println("Day " + (i + 1) + " is free: " + freeDays[i]);
      }

      System.out.println("[LOG] bookings retrieved: " + bookings.size());

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

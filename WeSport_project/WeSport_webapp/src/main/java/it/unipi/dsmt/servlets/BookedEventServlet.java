package it.unipi.dsmt.servlets;

import it.unipi.dsmt.dto.FieldBookingDTO;
import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.FieldBookingRemote;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BookedEventServlet", value = "/bookedEvent")
public class BookedEventServlet extends HttpServlet {

    @EJB
    private FieldBookingRemote fieldBookingRemote;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("logged_user");

        List<FieldBookingDTO> bookings = new ArrayList<>();
        System.out.println("[LOG] username: " + user.getUsername());

        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);

        bookings = fieldBookingRemote.displayBookingForSport("tennis");
        boolean[] freeDays = fieldBookingRemote.displayBusyDaysForMonth("tennis", date);
        for(int i = 0; i < freeDays.length; i++) {
            System.out.println("Day " + (i + 1) + " is free: " + freeDays[i]);
        }

        System.out.println("[LOG] bookings retrieved: " + bookings.size());

        request.setAttribute("bookings", bookings);
        request.setAttribute("freeDays", freeDays);

        String targetJSP = "/pages/jsp/bookedevent.jsp";

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetJSP);
        requestDispatcher.forward(request,response);
    }



}
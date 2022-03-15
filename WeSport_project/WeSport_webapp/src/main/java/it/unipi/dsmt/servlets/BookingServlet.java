package it.unipi.dsmt.servlets;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unipi.dsmt.dto.FieldBookingDTO;
import it.unipi.dsmt.interfaces.FieldBookingRemote;
import it.unipi.dsmt.interfaces.UserRemote;

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

    String username_ = request.getParameter("logged_user");

    List<FieldBookingDTO> bookings = new ArrayList<>();
    System.out.println("[LOG] username: " + username_);

    try {
      bookings = fieldBookingRemote.displayBooking(username_);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    HttpSession session = request.getSession();
    session.setAttribute("bookings", bookings);
    String targetJSP = "/pages/jsp/booking.jsp";

    RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetJSP);
    requestDispatcher.forward(request,response);
  }
}


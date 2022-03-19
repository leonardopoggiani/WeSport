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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {

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

        try {
            bookings = fieldBookingRemote.displayBooking(user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("[LOG] bookings retrieved: " + bookings.size());

        request.setAttribute("bookings", bookings);

        String targetJSP = "/pages/jsp/profile.jsp";

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetJSP);
        requestDispatcher.forward(request,response);
    }
}
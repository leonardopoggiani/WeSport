package it.unipi.dsmt.servlets;

import it.unipi.dsmt.dto.FieldBookingDTO;
import it.unipi.dsmt.interfaces.FieldBookingRemote;
import it.unipi.dsmt.interfaces.UserRemote;
import it.unipi.dsmt.utils.Utils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Set;

@WebServlet(name = "ShowBookingServlet", value = "/showbooking")
public class ShowBookingServlet extends HttpServlet {
    @EJB
    FieldBookingRemote fieldBookingRemote;
    @EJB
    UserRemote user;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sport = request.getParameter("sports");
        ArrayList<FieldBookingDTO> ret = new ArrayList<>();
        if (request.getParameter("username") == null || request.getParameter("username").equals("") || request.getParameter("username").equals(" ")){
            ret = fieldBookingRemote.displayBookingByFilter(null, sport);

        }
        else {
            String username = request.getParameter("username");
            try {
                Integer id = user.getUser(username).getId();
                ret = fieldBookingRemote.displayBookingByFilter(id, sport);
            } catch (Exception e) {
                request.setAttribute("list_bookings", ret);
            }

        }

        request.setAttribute("list_bookings", ret);

        request.getServletContext().getRequestDispatcher("/pages/jsp/removebooking.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}

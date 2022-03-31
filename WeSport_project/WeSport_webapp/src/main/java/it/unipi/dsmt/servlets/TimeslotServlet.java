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


@WebServlet(name = "TimeslotServlet", value = "/timeslot")
public class TimeslotServlet extends HttpServlet {
    @EJB
    private FieldBookingRemote fieldBookingRemote;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("logged_user");
        FieldBookingDTO booking = (FieldBookingDTO) session.getAttribute("fieldBooking");
        String timeslot = request.getParameter("timeslot");
        System.out.println("LOG: DoGet click "+timeslot);
        if(timeslot !=null){
            int time = Integer.parseInt(timeslot);
            booking.setStart_hour(time);
            booking.setEnd_hour(time+1);
            session.setAttribute("fieldBooking", booking);
            response.sendRedirect(request.getContextPath()+"/bookfield");
        }
        else{
            getServletContext().getRequestDispatcher("/pages/jsp/timeslot.jsp").forward(request, response);
        }
    }
}

package it.unipi.dsmt.servlets;

import it.unipi.dsmt.dto.FieldBookingDTO;
import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.FieldBookingRemote;
import it.unipi.dsmt.interfaces.UserBookingRemote;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RemoveBookingServlet", value = "/removebooking")
public class RemoveBookingServlet extends HttpServlet {
    @EJB
    FieldBookingRemote fieldBookingRemote;
    @EJB
    UserBookingRemote userBookingRemote;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String delete_booking = request.getParameter("bookingid");
        if (delete_booking != null){

            fieldBookingRemote.deleteBooking(Integer.parseInt(delete_booking));
            Integer[] user_booking_ids = userBookingRemote.retrieveRowsRelatedtoABooking(Integer.parseInt(delete_booking));
            if (user_booking_ids != null){
                for (Integer user_booking_id : user_booking_ids) {
                    userBookingRemote.deleteBooking(user_booking_id);
                }

            }

        }

        request.getServletContext().getRequestDispatcher("/pages/jsp/removebooking.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

}

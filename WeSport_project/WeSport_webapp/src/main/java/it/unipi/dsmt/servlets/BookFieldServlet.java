package it.unipi.dsmt.servlets;

import it.unipi.dsmt.dto.FieldBookingDTO;
import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.FieldBookingRemote;
import it.unipi.dsmt.interfaces.UserBookingRemote;
import it.unipi.dsmt.interfaces.UserRemote;
import it.unipi.dsmt.utils.Utils;

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
import java.sql.Time;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BookFieldServlet", value = "/bookfield")
public class BookFieldServlet extends HttpServlet {
    @EJB
    private FieldBookingRemote fieldBookingRemote;
    @EJB
    private UserBookingRemote userBookingRemote;
    @EJB
    private UserRemote userRemote;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/jsp/bookfield.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session =  request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("logged_user");
        FieldBookingDTO booking = (FieldBookingDTO) session.getAttribute("fieldBooking");
        String sport = booking.getSport();
        int numPlayer = 0;
        switch (sport){
            case "tennis": numPlayer = 2; break;
            case "rugby": numPlayer = 14; break;
            default: numPlayer = 10;
        }

        String players[] = new String[numPlayer];
        players[0]=request.getParameter("1");
        if(players[0]==null){
            getServletContext().getRequestDispatcher("/pages/jsp/bookfield.jsp").forward(request, response);
        }

        Integer[] usersID = new Integer[numPlayer];
        UserDTO userDTO = new UserDTO();
        for (int i=0; i < numPlayer; i++){
            String j = String.valueOf(i+1);
            players[i]=request.getParameter(j);
            if(players[i]==null){
                break;
            }
            else {
                try {
                    userDTO = userRemote.getUser(players[i]);
                    usersID[i] = userDTO.getId();
                } catch (SQLException e) {
                    System.err.println("LOG: Error in insertBooking");
                    response.sendRedirect(request.getContextPath()+"/bookfield");
                }
            }
        }

        try {
            fieldBookingRemote.insertBooking(sport, booking.getDay(), booking.getStart_hour(), booking.getEnd_hour(), booking.getBooker());
            Integer idBooking = fieldBookingRemote.lastBookingInserted();
            userBookingRemote.insertNewBooking(usersID, idBooking );
            response.sendRedirect(request.getContextPath()+"/homepage");
        }
        catch (SQLException e){
            System.err.println("LOG: Error in insertBooking");
            response.sendRedirect(request.getContextPath()+"/bookfield");
        }
    }

}


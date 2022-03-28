package it.unipi.dsmt.servlets;

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
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet(name = "BookFieldServlet", value = "/bookfield")
public class BookFieldServlet extends HttpServlet {
    @EJB
    private FieldBookingRemote fieldBookingRemote;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session =  request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("logged_user");
        FieldBookingDTO booking = (FieldBookingDTO) session.getAttribute("fieldBooking");

        String sport = booking.getSport();
        int numPlayer = 0;
        switch (sport){
            case "tennis": numPlayer = 2;
            case "rugby": numPlayer = 14;
            case "basket": numPlayer = 10;
            default: numPlayer = 10;
        }
        String players[] = new String[numPlayer];
        boolean ret = false;
        for (int i=0; i<numPlayer; i++){
            players[i]=request.getParameter(Integer.toString(i));
            if(players[i]==null){
                System.err.println("LOG: Player's username is missing");
                ret = true;
                break;

            }
        }

        if (!ret){
            getServletContext().getRequestDispatcher("/pages/jsp/bookfield.jsp").forward(request, response);
        }


    }

}


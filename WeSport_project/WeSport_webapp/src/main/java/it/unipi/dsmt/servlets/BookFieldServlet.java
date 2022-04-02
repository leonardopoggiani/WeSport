package it.unipi.dsmt.servlets;

import it.unipi.dsmt.dto.FieldBookingDTO;
import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.FieldBookingRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "BookFieldServlet", value = "/bookfield")
public class BookFieldServlet extends HttpServlet {
    @EJB
    private FieldBookingRemote fieldBookingRemoteEJB;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/jsp/bookfield.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session =  request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("logged_user");
        FieldBookingDTO booking = (FieldBookingDTO) session.getAttribute("fieldBooking");
        System.out.println("LOG:Nella post bookfield");
        String sport = booking.getSport();
        System.out.println("Sport:" + sport);
        int numPlayer = 0;
        switch (sport){
            case "tennis": numPlayer = 2; break;
            case "rugby": numPlayer = 14; break;
            default: numPlayer = 10;
        }

        String players[] = new String[numPlayer];
        players[0]=request.getParameter("1");
        System.out.println("LOG:Player" + players[0]);
        if(players[0]==null){
            getServletContext().getRequestDispatcher("/pages/jsp/bookfield.jsp").forward(request, response);
        }

        for (int i=0; i<numPlayer; i++){
            String j = String.valueOf(i+1);
            players[i]=request.getParameter(j);
            System.out.println("LOG:" + players[i] + " ," + j);
            if(players[i]==null){
                System.out.println("LOG:" + players[i]);
                break;

            }
        }

        try {
            fieldBookingRemoteEJB.insertBooking(sport, booking.getDay(), booking.getStart_hour(), booking.getEnd_hour(), booking.getBooker());
            response.sendRedirect(request.getContextPath()+"/homepage");
        }
        catch (SQLException e){
            System.err.println("LOG: Error in insertBooking");
            response.sendRedirect(request.getContextPath()+"/bookfield");
            //getServletContext().getRequestDispatcher("/pages/jsp/homepage.jsp").forward(request, response);
        }
        //getServletContext().getRequestDispatcher("/pages/jsp/homepage.jsp").forward(request, response);


    }

}

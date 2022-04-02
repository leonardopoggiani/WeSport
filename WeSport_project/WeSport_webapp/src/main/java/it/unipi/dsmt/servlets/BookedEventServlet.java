package it.unipi.dsmt.servlets;

import it.unipi.dsmt.dto.FieldBookingDTO;
import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.UserBookingRemote;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@WebServlet(name = "BookedEventServlet", value = "/bookedEvent")
public class BookedEventServlet extends HttpServlet {

    @EJB
    private UserRemote userRemoteEJB;
    @EJB
    private UserBookingRemote bookingUserRemoteEJB;
    @EJB
    private FieldBookingRemote fieldBookingRemoteEJB;

    public Integer bookingIdentifier;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String score = request.getParameter("inputScore");

        String targetJSP = "/pages/jsp/bookedevent.jsp";
        Integer bookingID = this.bookingIdentifier;
        ArrayList<UserDTO> friends;
        //friends=userRemote.displayUsersForEvent(bookingID);
        //request.setAttribute("friends", friends);
        String userID= request.getParameter("userId");
       // boolean ins=bookingUserRemote.updateScore(bookingUserRemote.displayUserBooking2(userID,2),2);
        session.getAttribute("inp");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetJSP);
        requestDispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("logged_user");
        //  Integer event;
        //session.setAttribute("event", event);
        Integer bookingID = Integer.valueOf(request.getParameter("event"));
        List<FieldBookingDTO> bookings ;


        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);

        bookings = fieldBookingRemoteEJB.displayBookingForSport("tennis");
        boolean[] freeDays = fieldBookingRemoteEJB.displayBusyDaysForMonth("tennis", date);

        for(int i = 0; i < freeDays.length; i++) {
            System.out.println("Day " + (i + 1) + " is free: " + freeDays[i]);
        }

        System.out.println("[LOG] bookings retrieved: " + bookings.size());

        request.setAttribute("bookings", bookings);
        request.setAttribute("freeDays", freeDays);



        System.out.println("[LOG] bookingID: " + bookingID);
        this.bookingIdentifier=bookingID;
        ArrayList<UserDTO> friends;
        friends=userRemoteEJB.displayUsersForEvent(bookingID);
        //Integer userbookingid=Integer.valueOf(request.getParameter());;
       // UserBookingDTO userBookingDTO=bookingUserRemote.displayUserBooking(userbookingid);
        request.setAttribute("friends", friends);
        session.setAttribute("event",bookingID);

        String targetJSP = "/pages/jsp/bookedevent.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetJSP);
        requestDispatcher.forward(request,response);
    }



}

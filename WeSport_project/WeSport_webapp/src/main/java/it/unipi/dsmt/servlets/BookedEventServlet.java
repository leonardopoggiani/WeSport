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
    private UserBookingRemote userBookingRemoteEJB;
    @EJB
    private FieldBookingRemote fieldBookingRemoteEJB;

    public Integer bookingIdentifier;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("DOPOST ");

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("logged_user");
        String score = request.getParameter("inputScore");
        String scored_id = request.getParameter("id");
        Integer event = (Integer) session.getAttribute("event");

        System.out.println("score: " + score);
        if(!score.isEmpty()){
            System.out.println("scoreint"+Integer.valueOf(score));
        }
        System.out.println("scored_id: " + scored_id);
        System.out.println("event: " + event);


        Integer bookingID = this.bookingIdentifier;
        ArrayList<UserDTO> friends;

        friends = userRemoteEJB.displayUsersForEvent(bookingID, user.getUsername());

        request.setAttribute("friends", friends);

        boolean ins;

        if(!score.isEmpty()) {
            if (Integer.valueOf(score) >= 0 && Integer.valueOf(score) <= 5)
                ins = userBookingRemoteEJB.updateScore(userBookingRemoteEJB.displayUserBooking2(Integer.valueOf(scored_id), event), Integer.valueOf(score));

        }
        session.getAttribute("inp");

        String targetJSP = "/pages/jsp/bookedevent.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetJSP);
        requestDispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("logged_user");

        Integer bookingID = Integer.valueOf(request.getParameter("event"));
        System.out.println("event: " + bookingID);

        List<FieldBookingDTO> bookings ;

        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);

        bookings = fieldBookingRemoteEJB.displayBookingForSport("tennis");

        System.out.println("[LOG] bookings retrieved: " + bookings.size());

        request.setAttribute("bookings", bookings);

        System.out.println("[LOG] bookingID: " + bookingID);
        this.bookingIdentifier = bookingID;

        ArrayList<UserDTO> friends;
        friends = userRemoteEJB.displayUsersForEvent(bookingID, user.getUsername());
        //Integer userbookingid = Integer.valueOf(request.getParameter());
        //UserBookingDTO userBookingDTO = userBookingRemoteEJB.displayUserBooking(userbookingid);
        request.setAttribute("friends", friends);

        for(int i = 0; i < friends.size(); i++) {
            System.out.println("friends " + (i + 1) + ": " + friends.get(i));
        }

        session.setAttribute("event",bookingID);

        String targetJSP = "/pages/jsp/bookedevent.jsp";

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetJSP);
        requestDispatcher.forward(request,response);
    }
}

package it.unipi.dsmt.servlets;

import it.unipi.dsmt.interfaces.FieldBookingRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookTimeslotServlet", value = "/booktimeslot")
public class BookTimeslotServlet {
    @EJB
    private FieldBookingRemote fieldBookingRemote;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }
}

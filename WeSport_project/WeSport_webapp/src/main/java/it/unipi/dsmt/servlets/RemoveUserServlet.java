package it.unipi.dsmt.servlets;

import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.UserBookingRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RootServlet", value = "/servlet")
public class RemoveUserServlet extends HttpServlet {
    @EJB
    private UserBookingRemote user;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/pages/jsp/root.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }
}

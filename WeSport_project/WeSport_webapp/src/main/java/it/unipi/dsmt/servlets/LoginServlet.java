package it.unipi.dsmt.servlets;

import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.ejb.UserRemoteEJB;
import it.unipi.dsmt.interfaces.UserRemote;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet",value = "/login")
public class LoginServlet extends HttpServlet {
    private UserRemote userRemoteEJB;

    {
        try {
            userRemoteEJB = new UserRemoteEJB();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        String username_ = request.getParameter("username");
        String password_ = request.getParameter("password");
        UserDTO logged_user = null;

        System.err.println("username" + username_);

        try {
            userRemoteEJB = new UserRemoteEJB();
            logged_user = userRemoteEJB.loginUser(username_,password_);
            System.err.println("LOGIN DONE");
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        assert logged_user != null;
        System.err.println("LOGGED USER " + logged_user.getUsername());

        HttpSession session = request.getSession();
        session.setAttribute("logged_user", logged_user);
        response.sendRedirect(request.getContextPath()+"/pages/jsp/homepage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

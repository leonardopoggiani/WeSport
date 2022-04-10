package it.unipi.dsmt.servlets;

import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.UserRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet",value = "/login")
public class LoginServlet extends HttpServlet {

    @EJB
    private UserRemote userRemoteEJB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        String username_ = request.getParameter("user");
        String password_ = request.getParameter("password");
        UserDTO logged_user = null;

        try {
            logged_user = userRemoteEJB.loginUser(username_,password_);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        session.setAttribute("logged_user", logged_user);
        if(logged_user == null) {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
        else if (logged_user.equals("root")){
            response.sendRedirect(request.getContextPath()+"/root");
        }
        else {
            response.sendRedirect(request.getContextPath()+"/homepage");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

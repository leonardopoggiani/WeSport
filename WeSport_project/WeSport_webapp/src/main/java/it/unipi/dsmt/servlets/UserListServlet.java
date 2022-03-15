package it.unipi.dsmt.servlets;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.UserRemote;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "UserListServlet", value = "/userlist")
public class UserListServlet extends HttpServlet {

    @EJB
    private UserRemote userRemote;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

        String resourceURL = null;
        String action = request.getParameter("action");
        String filter_username = request.getParameter("filter");
        String code = request.getParameter("code");

        System.out.println("[LOG] action: " + action);
        System.out.println("[LOG] username: " + filter_username);
        System.out.println("[LOG] code: " + code);

        List<UserDTO> users = new ArrayList<>();

        if(filter_username != null && filter_username.compareTo("") != 0) {
            UserDTO user = new UserDTO();

            try {
                System.out.println("[LOG] filter_username: " + filter_username);

                user = userRemote.getUser(filter_username);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            users.add(user);

            request.setAttribute("users", users);
            resourceURL = "/pages/jsp/userslist.jsp";

        } else {
            try {
                System.out.println("[LOG] list");

                users = userRemote.listUsers();
            } catch (Exception e) {
                e.printStackTrace();
            }

            request.setAttribute("users", users);
            resourceURL = "/pages/jsp/userslist.jsp";
        }

        RequestDispatcher rd = request.getRequestDispatcher(resourceURL);
        rd.forward(request, response);
    }

}


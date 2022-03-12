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
    String username = request.getParameter("filter");
    String code = request.getParameter("code");

    List<UserDTO> users = new ArrayList<>();

    if(action == null || action.compareTo("load") != 0) {
        if(username != null && username.compareTo("") != 0) {
            UserDTO user = new UserDTO();
            try {
                user = userRemote.getUser(username);
                users.add(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(username == null || username.compareTo("") == 0) {
            try {
                users = userRemote.listUsers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("users", users);
        resourceURL = "/pages/jsp/userslist.jsp";
    } else {
        UserDTO user = new UserDTO();
        try {
          user = userRemote.getUser(code);
        } catch (Exception e) {
          e.printStackTrace();
        }
        request.setAttribute("user", user);
        resourceURL = "/pages/jsp/userpage.jsp";
    }

    RequestDispatcher rd = request.getRequestDispatcher(resourceURL);
    rd.forward(request, response);
  }

}


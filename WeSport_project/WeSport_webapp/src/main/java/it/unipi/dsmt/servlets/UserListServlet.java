package it.unipi.dsmt.servlets;


import java.io.IOException;
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


@WebServlet(name = "UserListServlet", value = "/UserListServlet")
public class UserListServlet extends HttpServlet {

    @EJB
    private UserRemote userRemote;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

      String resourceURL = null;
      String action = request.getParameter("action");
      List<UserDTO> users = null;

      if (action != null) {
          users = null;
          try {
              users = userRemote.listUsers();
          } catch (Exception e) {
              e.printStackTrace();
          }
      }

      resourceURL = "/pages/jsp/users.jsp";
      request.setAttribute("users", users);

      RequestDispatcher rd = request.getRequestDispatcher(resourceURL);
      rd.forward(request, response);
  }
}


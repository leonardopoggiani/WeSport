package it.unipi.dsmt.servlets;


import java.io.IOException;
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
import javax.servlet.http.HttpSession;

@WebServlet(name = "ChatServlet", value = "/chat")
public class ChatServlet extends HttpServlet {

    @EJB
    private UserRemote userRemote;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {
        String targetJSP = "";
        List<UserDTO> users = new ArrayList<>();

        HttpSession session = request.getSession();
        UserDTO logged_user = (UserDTO) session.getAttribute("logged_user");


        if(logged_user == null) {
            targetJSP = "/index.jsp";
        } else {
            targetJSP = "/pages/jsp/chat.jsp";
        }

        try {
            users = userRemote.listUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("users", users);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(targetJSP);
        requestDispatcher.forward(request,response);
    }
}

package it.unipi.dsmt.servlets;

import it.unipi.dsmt.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetJSP = "";
        HttpSession session = request.getSession();
        UserDTO logged_user = (UserDTO) session.getAttribute("logged_user");

        if(logged_user == null) {
            targetJSP = "/index.jsp";
        } else {
            targetJSP = "/pages/jsp/admin.jsp";
        }

        request.getServletContext().getRequestDispatcher(targetJSP).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }
}

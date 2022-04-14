package it.unipi.dsmt.servlets;

import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.UserRemote;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RemoveUserServlet", value = "/removeuser")
public class RemoveUserServlet extends HttpServlet {
    @EJB
    private UserRemote user;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String delete_user = request.getParameter("username");
        if (delete_user != null){
            UserDTO delete_userDTO = new UserDTO();
            try {
                delete_userDTO= user.getUser(delete_user);
            } catch (SQLException e) {
                System.out.println("LOG: Problems in getting userDTO");
            }
            user.deleteUser(delete_userDTO.getId());

        }

        List<UserDTO> list_users = new ArrayList<>();

        try {
            list_users = user.listUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("list_users", list_users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/jsp/removeuser.jsp");
        requestDispatcher.forward(request,response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }
}

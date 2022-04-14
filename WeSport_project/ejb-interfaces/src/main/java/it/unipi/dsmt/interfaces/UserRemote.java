package it.unipi.dsmt.interfaces;
import it.unipi.dsmt.dto.UserDTO;

import javax.ejb.Remote;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Remote
public interface UserRemote {

    List<UserDTO> listUsers() throws Exception;

    UserDTO getUser(String username) throws SQLException;

    UserDTO loginUser(String username, String password) throws SQLException;

    ArrayList<UserDTO> displayUsersForEvent(Integer event_id, String logged_user);

    void deleteUser (Integer id);
}

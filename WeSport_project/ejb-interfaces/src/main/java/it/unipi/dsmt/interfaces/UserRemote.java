package it.unipi.dsmt.interfaces;

import it.unipi.dsmt.dto.UserDTO;

import javax.ejb.Remote;
import java.sql.SQLException;
import java.util.List;

@Remote
public interface UserRemote {

    List<UserDTO> listUsers() throws Exception;

    UserDTO getUser(String username) throws SQLException;
}

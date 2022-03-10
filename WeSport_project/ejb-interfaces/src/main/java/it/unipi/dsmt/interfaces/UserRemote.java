package it.unipi.dsmt.interfaces;

import it.unipi.dsmt.dto.UserDTO;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface UserRemote {

    public List<UserDTO> listUsers(String name) throws Exception;

}

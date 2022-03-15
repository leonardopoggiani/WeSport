package it.unipi.dsmt.ejb;


import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.ejb.entities.User;
import it.unipi.dsmt.interfaces.UserRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserRemoteEJB implements UserRemote {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserDTO> listUsers() throws Exception {
        List<UserDTO> result = new ArrayList<>();

        String jpql = "select u.id, u.username, u.name, u.surname, u.email, u.city, u.postal_code, u.description from User u";

        Query query = entityManager.createQuery(jpql);

        List<User> userList = query.getResultList();
        if (userList != null && !userList.isEmpty()) {
            for(User user: userList){
                Integer id = user.getId();
                UserDTO dto = new UserDTO();
                dto.setId(user.getId());
                dto.setUsername(user.getUsername());
                dto.setName(user.getName());
                dto.setSurname(user.getSurname());
                dto.setEmail(user.getEmail());
                dto.setCity(user.getCity());
                dto.setPostal_code(user.getPostal_code());
                dto.setDescription(user.getDescription());

                result.add(dto);
            }
        }

        return result;
    }

    @Override
    public UserDTO getUser(String username) throws SQLException {
        String jpql = "select u.id, u.username, u.name, u.surname, u.email, u.password, u.city, u.postal_code, u.description " +
                "from User u where lower(u.username) = lower(:username)";
        
        Query query = entityManager.createQuery(jpql);
        query.setParameter("username", username);

        List<Object[]> results = query.getResultList();

        if (results != null && !results.isEmpty()) {
            for(Object[] userInfo: results){
                UserDTO dto = new UserDTO();
                dto.setId((Integer) userInfo[0]);
                dto.setUsername((String) userInfo[1]);
                dto.setName((String) userInfo[2]);
                dto.setSurname((String) userInfo[3]);
                dto.setEmail((String) userInfo[4]);
                dto.setPassword((String) userInfo[5]);
                dto.setCity((String) userInfo[6]);
                dto.setPostal_code((Integer) userInfo[7]);
                dto.setDescription((String) userInfo[8]);

                return dto;
            }
        }

        return null;
    }

    @Override
    public UserDTO loginUser(String username, String password) throws SQLException {

        UserDTO logged_user = getUser(username);

        if(logged_user != null && logged_user.getPassword().compareTo(password) == 0) {
            return logged_user;
        } else {
            return null;
        }

    }
}

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

        List<Object[]> userList = query.getResultList();

        if (userList != null && !userList.isEmpty()) {
            for(Object[] user: userList){

                if( ((String) user[1]).compareTo("admin") == 0) {
                    continue;
                }

                UserDTO dto = new UserDTO();
                dto.setId((Integer) user[0]);
                dto.setUsername((String) user[1]);
                dto.setName((String) user[2]);
                dto.setSurname((String) user[3]);
                dto.setEmail((String) user[4]);
                dto.setCity((String) user[5]);
                dto.setPostal_code((Integer) user[6]);
                dto.setDescription((String) user[7]);

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

    @Override
    public ArrayList<UserDTO> displayUsersForEvent(Integer event_id, String logged_user) {
        ArrayList<UserDTO> userDTOS = new ArrayList<>();

        String jpql = "select ub.userBookingid,u.id, u.username, u.name, u.surname" +
                " from User u join UserBooking ub " +
                " where u.id = ub.userID and ub.bookingID = :event_id " +
                " and u.username <> :logged_user";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("event_id", event_id);
        query.setParameter("logged_user", logged_user);

        List<Object[]> userList = query.getResultList();

        if (userList != null && !userList.isEmpty()) {
            for(Object[] userInfo: userList){
                UserDTO dto = new UserDTO();

                dto.setId((Integer) userInfo[1]);
                dto.setUsername((String) userInfo[2]);
                dto.setName((String) userInfo[3]);
                dto.setSurname((String) userInfo[4]);

                userDTOS.add(dto);
            }
        }
        return  userDTOS;
    }

    @Override
    public void deleteUser (Integer id){

        User user = entityManager.find(User.class, id );
        entityManager.remove(user);

    }

}

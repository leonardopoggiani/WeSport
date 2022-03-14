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
        StringBuilder jpql = new StringBuilder();
        List<UserDTO> result = new ArrayList<>();

        jpql.append("select ");
        jpql.append("u.ID, ");
        jpql.append("u.username, ");
        jpql.append("u.name, ");
        jpql.append("u.surname, ");
        jpql.append("u.email, ");
        jpql.append("u.city, ");
        jpql.append("u.postal_code, ");
        jpql.append("u.description ");
        jpql.append("from user u");

        Query query = entityManager.createQuery(jpql.toString());

        List<Object[]> userList = query.getResultList();
        if (userList != null && !userList.isEmpty()) {
            for(Object[] userInfo: userList){
                User user = (User)userInfo[0];
                Integer id = ((Number)userInfo[1]).intValue();
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
        return null;
    }

    /*
    @Override
    public List<UserDTO> listUsers() throws Exception {
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        List<UserDTO> result = new ArrayList<>();
        try{
            connection = dataSource.getConnection();
            StringBuilder sqlStringBuilder = new StringBuilder();

            sqlStringBuilder.append("select * ");
            sqlStringBuilder.append("from user  ");
            pstm = connection.prepareStatement(sqlStringBuilder.toString());
            rs = pstm.executeQuery();
            while (rs.next()){
                UserDTO dto = new UserDTO();
                dto.setUser_id(rs.getString(1));
                dto.setUsername(rs.getString(2));
                dto.setName(rs.getString(3));
                dto.setSurname(rs.getString(4));
                dto.setEmail(rs.getString(5));
                dto.setCity(rs.getString(7));

                result.add(dto);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstm != null) {
                rs.close();
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }
        return result;
    } */

    /*
    @Override
    public UserDTO getUser(String username) throws SQLException{
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UserDTO logged_user = new UserDTO();

        try{
            connection = dataSource.getConnection();
            StringBuilder sqlStringBuilder = new StringBuilder();
            sqlStringBuilder.append("select ");
            sqlStringBuilder.append("  *  "); // TODO: non *, non devo poter vedere la password con una get
            sqlStringBuilder.append("from user ");
            sqlStringBuilder.append("where username = ?;");
            pstm = connection.prepareStatement(sqlStringBuilder.toString());

            if (username != null) {
                pstm.setString(1, username);
            }
            rs = pstm.executeQuery();
            if (rs.next()){
                logged_user.setUsername(rs.getString(2));
                logged_user.setName(rs.getString(3));
                logged_user.setSurname(rs.getString(4));
                logged_user.setEmail(rs.getString(5));

                return logged_user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            assert connection != null;
            connection.close();
            assert pstm != null;
            pstm.close();
        }

        return null;
    }*/

  @Override
  public UserDTO loginUser(String username, String password) {

    UserDTO logged_user = new UserDTO();
    String jpql = "select u from User u where lower(u.username) = lower(:username)";
    Query query = entityManager.createQuery(jpql);
    query.setParameter("username", username);
    List<User> results = query.getResultList();
    User user = null;
    if (results != null && !results.isEmpty()){
        user = results.get(0);
        System.out.println("User found " + user.getId());
    }
    if(user != null && user.getPassword().compareTo(password) == 0) {
        logged_user.setId(user.getId());
        logged_user.setUsername(user.getUsername());
        logged_user.setName(user.getName());
        logged_user.setSurname(user.getSurname());
        logged_user.setEmail(user.getEmail());
        logged_user.setCity(user.getCity());
        logged_user.setPostal_code(user.getPostal_code());
        logged_user.setDescription(user.getDescription());
    }

    return logged_user;
  }
}

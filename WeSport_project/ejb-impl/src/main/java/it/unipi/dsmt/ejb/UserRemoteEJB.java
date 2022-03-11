package it.unipi.dsmt.ejb;

import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.ejb.entities.User;
import it.unipi.dsmt.interfaces.UserRemote;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserRemoteEJB implements UserRemote {

    @Resource(lookup = "jdbc/wesport_pool")
    private DataSource dataSource;

    @PersistenceContext
    private EntityManager entityManager;

    public UserRemoteEJB() throws NamingException {
        Context ctx = new InitialContext();
        dataSource = (DataSource) ctx.lookup("jdbc/wesport_pool");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("wesportPU");
        entityManager = emf.createEntityManager();
    }


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
    }

    @Override
    public UserDTO getUser(String username) throws SQLException{
        User user = entityManager.createQuery(
                        "SELECT u from User u WHERE u.username = :username", User.class).
                setParameter("username", username).getSingleResult();
        Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from User where user_id='" + user.getUser_id() + "'");
        ArrayList<String> pets = new ArrayList<>();
        if(rs.next()){
            if(rs.getBoolean(2)) pets.add("dog");
            if(rs.getBoolean(3)) pets.add("cat");
            if(rs.getBoolean(4)) pets.add("rabbit");
            if(rs.getBoolean(5)) pets.add("hamster");
        }
        UserDTO userDTO = new UserDTO();
        con.close();
        return userDTO;
    }
}

package it.unipi.dsmt.ejb;


import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.ejb.entities.User;
import it.unipi.dsmt.interfaces.UserRemote;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;
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
    }

    @Override
    public UserDTO loginUser(String username, String password) throws SQLException{

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UserDTO logged_user = new UserDTO();

        try{
            connection = dataSource.getConnection();
            StringBuilder sqlStringBuilder = new StringBuilder();
            sqlStringBuilder.append("select ");
            sqlStringBuilder.append("  *  ");
            sqlStringBuilder.append("from user ");
            sqlStringBuilder.append("where username = ?;");
            pstm = connection.prepareStatement(sqlStringBuilder.toString());
            if (username != null) {
                pstm.setString(1, username);
            }
            rs = pstm.executeQuery();
            if (rs.next()){
                if(rs.getString(6).compareTo(password) == 0){
                    logged_user.setUsername(rs.getString(2));
                    logged_user.setName(rs.getString(3));
                    logged_user.setSurname(rs.getString(4));
                    logged_user.setEmail(rs.getString(5));

                    return logged_user;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            assert connection != null;
            connection.close();
            assert pstm != null;
            pstm.close();
        }

        return null;
    }
}

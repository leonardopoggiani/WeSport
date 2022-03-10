package it.unipi.dsmt.ejb;

import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.ejb.entities.User;
import it.unipi.dsmt.interfaces.UserRemote;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class UserRemoteEJB implements UserRemote {

    @Resource(lookup = "jdbc/wesport_pool")
    private DataSource dataSource;

    // step 0: configure your META-INF/persistence.xml file

    // step 1: get a reference of a EntityManager by using the @PersistenceContext annotation

    @PersistenceContext
    private EntityManager entityManager;
    // step 2: create the entity bean Country which maps the database table world.country

    // step 3: implements the methods: listCountriesJPA, findByCodeJPA and saveOrUpdateJPA
    @Override
    public List<UserDTO> listUsers(String name) throws Exception {
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
            if (name != null && !name.isEmpty()) {
                pstm.setString(1, name);
            }
            rs = pstm.executeQuery();
            while (rs.next()){
                UserDTO dto = new UserDTO();
                dto.setUser_id(rs.getString(1));
                dto.setName(rs.getString(2));
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
                } catch (SQLException e) {
                }
            }
        }
        return result;
    }
}

package it.unipi.dsmt.ejb;

import it.unipi.dsmt.dto.FieldBookingDTO;
import it.unipi.dsmt.ejb.entities.FieldBooking;
import it.unipi.dsmt.interfaces.FieldBookingRemote;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class FieldBookingEJB implements FieldBookingRemote {

    private static DataSource dataSource = null;

    @PersistenceContext
    private EntityManager entityManager;

    public FieldBookingEJB() throws NamingException {
        Context ctx = new InitialContext();
        dataSource = (DataSource) ctx.lookup("jdbc/wesport_pool");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("wesportPU");
        entityManager = emf.createEntityManager();
    }

    @Override
    public void insertPendingBooking(String ps_id,String ps_us,String po_id, String po_us, String from, String to,String pet_str) throws SQLException {
        Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        stmt.executeUpdate("INSERT INTO booking (petowner_id,petowner_username, petsitter_id, petsitter_username, from_date, to_date, pet,status)\n" +
                "VALUES (" + "'" + po_id + "'," + "'" + po_us + "'," + "'" + ps_id + "'," + "'" + ps_us + "'," + "'" + from + "'," + "'" + to + "'," + "'" + pet_str + "'," + "'pending');");

        con.close();
    }

    @Override
    public ArrayList<FieldBookingDTO> displayBooking(String username, boolean petsitter, boolean pending) {
        String petowner_us = null;
        String petsitter_us = null;

        return null;
    }

    @Override
    public void removePendingBooking(String booking_id, boolean accepted) throws SQLException {

    }

    @Override
    public boolean searchBooking(String owner, String petSitter) throws SQLException {
        return false;
    }

}

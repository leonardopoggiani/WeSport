package it.unipi.dsmt.interfaces;

import it.unipi.dsmt.dto.FieldBookingDTO;

import javax.ejb.Remote;
import java.sql.SQLException;
import java.util.ArrayList;

@Remote
public interface FieldBookingRemote {

    public void insertPendingBooking(String id, String sport) throws SQLException;
    public ArrayList<FieldBookingDTO> displayBooking(String username, boolean petsitter, boolean pending) throws SQLException;
    public void removePendingBooking(String booking_id, boolean accepted) throws SQLException;

    boolean searchBooking(String owner, String petSitter) throws SQLException;
}

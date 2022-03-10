package it.unipi.dsmt.interfaces;

import it.unipi.dsmt.dto.FieldBookingDTO;

import javax.ejb.Remote;
import java.sql.SQLException;
import java.util.ArrayList;

@Remote
public interface FieldBookingRemote {

    public void insertPendingBooking(String ps_id, String ps_us, String po_id, String po_us, String from, String to, String pet_str) throws SQLException;
    public ArrayList<FieldBookingDTO> displayBooking(String username,boolean petsitter,boolean pending ) throws SQLException;
    public void removePendingBooking(String booking_id, boolean accepted) throws SQLException;

    boolean searchBooking(String owner, String petSitter) throws SQLException;
}

package it.unipi.dsmt.interfaces;

import it.unipi.dsmt.dto.FieldBookingDTO;

import javax.ejb.Remote;
import java.sql.SQLException;
import java.util.ArrayList;

@Remote
public interface FieldBookingRemote {

    public void insertBooking(Integer id, String sport) throws SQLException;
    public ArrayList<FieldBookingDTO> displayBooking(String username) throws SQLException;
    public ArrayList<FieldBookingDTO> displayBookingForSport(String sport);
}

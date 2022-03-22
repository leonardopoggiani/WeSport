package it.unipi.dsmt.interfaces;

import it.unipi.dsmt.dto.FieldBookingDTO;

import javax.ejb.Remote;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Remote
public interface FieldBookingRemote {

    public void insertBooking(Integer id, String sport) throws SQLException;
    public ArrayList<FieldBookingDTO> displayBooking(Integer user_id) throws SQLException;
    public ArrayList<FieldBookingDTO> displayBookingForSport(String sport);

    public ArrayList<FieldBookingDTO> displayBookingNotExpired(String sport);
    public ArrayList<FieldBookingDTO> displayBookingExpired(String sport);
    public boolean[] displayBusyDaysForMonth(String sport, Date currentMonth);

}

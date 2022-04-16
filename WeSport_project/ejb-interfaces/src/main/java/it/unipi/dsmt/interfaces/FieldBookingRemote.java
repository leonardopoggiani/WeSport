package it.unipi.dsmt.interfaces;

import it.unipi.dsmt.dto.FieldBookingDTO;

import javax.ejb.Remote;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Remote
public interface FieldBookingRemote {

    public void insertBooking(String sport, Date date, Integer start_hour, Integer end_hour, Integer booker) throws SQLException;
    public ArrayList<FieldBookingDTO> displayBooking(Integer user_id) throws SQLException;
    public ArrayList<FieldBookingDTO> displayBookingForSport(String sport);
    public ArrayList<FieldBookingDTO> displayBookingByFilter(Integer bookerID, String sport);
    public int lastBookingInserted ();
    public ArrayList<FieldBookingDTO> displayBookingNotExpired(String sport);
    public ArrayList<FieldBookingDTO> displayBookingExpired(String sport);
    public boolean[] displayBusyDaysForMonth(String sport, Date currentMonth);
    public boolean[] displayBusyTimeslotForDay(String sport, Date selectedDate);
    public void deleteBooking(Integer idBooking);

}

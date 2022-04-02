package it.unipi.dsmt.interfaces;

import it.unipi.dsmt.dto.FieldBookingDTO;
import javax.ejb.Remote;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Remote
public interface FieldBookingRemote {

    void insertBooking(String sport, Date date, Integer start_hour, Integer end_hour, Integer booker) throws SQLException;
    ArrayList<FieldBookingDTO> displayBooking(Integer user_id) throws SQLException;
    ArrayList<FieldBookingDTO> displayBookingForSport(String sport);

    ArrayList<FieldBookingDTO> displayBookingNotExpired(String sport);
    ArrayList<FieldBookingDTO> displayBookingExpired(String sport);
    boolean[] displayBusyDaysForMonth(String sport, Date currentMonth);

}

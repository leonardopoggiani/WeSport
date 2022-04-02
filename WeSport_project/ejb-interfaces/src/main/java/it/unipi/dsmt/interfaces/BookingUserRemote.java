package it.unipi.dsmt.interfaces;

import it.unipi.dsmt.dto.UserBookingDTO;

import javax.ejb.Remote;
import javax.persistence.criteria.CriteriaBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Remote
public interface BookingUserRemote {
    void setScore(Integer score);
    boolean updateScore(Integer userBookingID, Integer score);
    UserBookingDTO displayUserBooking(Integer user_booking_id);
    Integer displayUserBooking2(Integer user_id, Integer booking_id);
}

package it.unipi.dsmt.interfaces;
import it.unipi.dsmt.dto.UserBookingDTO;

import javax.ejb.Remote;

@Remote
public interface UserBookingRemote {
    public void setScore(Integer score);
    public UserBookingDTO displayUserBooking(Integer user_booking_id);
    public Integer displayUserBooking2(Integer user_id, Integer booking_id);
    public boolean updateScore(Integer userBookingID, Integer score);
}

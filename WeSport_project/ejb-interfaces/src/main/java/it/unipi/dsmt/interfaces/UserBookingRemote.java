package it.unipi.dsmt.interfaces;

import it.unipi.dsmt.dto.UserBookingDTO;

import javax.ejb.Remote;

@Remote
public interface UserBookingRemote {

    void setScore(Integer score);
    boolean updateScore(Integer userBookingID, Integer score);
    UserBookingDTO displayUserBooking(Integer user_booking_id);
    Integer displayUserBooking2(Integer user_id, Integer booking_id);
    double retrieveScore(String user_id);
    public void deleteBooking(Integer id_user_booking);
    public void insertNewBooking (Integer[] users, Integer idBooking);
    public Integer[] retrieveRowsRelatedtoABooking (int bookingID);
}

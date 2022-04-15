package it.unipi.dsmt.ejb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="user_booking")
public class UserBooking implements Serializable{

    @Id
    @Column(name="user_booking_ID")
    private Integer userBookingid;

    @Column(name="user_ID")
    private Integer userID;

    @Column(name="booking_ID")
    private Integer bookingID;

    @Column(name="score")
    private Integer score;

    public Integer getUserBookingid() {
        return userBookingid;
    }

    public void setUserBookingid(Integer userBookingid) {
        this.userBookingid = userBookingid;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getBookingID() {
        return getBookingID();
    }

    public void setBookingID(Integer bookingID) {
        this.bookingID = bookingID;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}

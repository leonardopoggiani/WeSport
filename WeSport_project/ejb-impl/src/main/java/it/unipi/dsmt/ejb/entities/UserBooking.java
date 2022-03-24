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
}

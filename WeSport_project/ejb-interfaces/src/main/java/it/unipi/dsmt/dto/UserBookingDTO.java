package it.unipi.dsmt.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserBookingDTO implements Serializable {

    private Integer userbooking_id;
    private Integer booking_id;
    private Integer user_id;


    public Integer getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Integer booking_id) {
        this.booking_id = booking_id;
    }

    public Integer getUserBooking_id() {
        return userbooking_id;
    }

    public void setUserbooking_id(Integer userbooking_id) {
        this.userbooking_id = userbooking_id;
    }

    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }


}




package it.unipi.dsmt.dto;


import java.io.Serializable;
import java.util.Date;

public class FieldBookingDTO implements Serializable {

    private Integer booking_id;
    private String sport;
    private Date day;
    private Integer start_hour;
    private Integer end_hour;
    private Integer booker;

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id='" + booking_id + '\'' +
                ", sport='" + sport + '\'' +
                '}';
    }

    public Integer getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Integer booking_id) {
        this.booking_id = booking_id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Integer getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(Integer start_hour) {
        this.start_hour = start_hour;
    }

    public Integer getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(Integer end_hour) {
        this.end_hour = end_hour;
    }

    public Integer getBooker() {
        return booker;
    }

    public void setBooker(Integer booker) {
        this.booker = booker;
    }
}

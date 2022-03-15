package it.unipi.dsmt.dto;


import java.sql.Date;
import java.sql.Timestamp;

public class FieldBookingDTO {

    private Integer booking_id;
    private String sport;
    private Date day;
    private Timestamp start_hour;
    private Timestamp end_hour;
    private String booker;

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

    public Timestamp getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(Timestamp start_hour) {
        this.start_hour = start_hour;
    }

    public Timestamp getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(Timestamp end_hour) {
        this.end_hour = end_hour;
    }

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }
}

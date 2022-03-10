package it.unipi.dsmt.dto;

import java.util.ArrayList;

public class FieldBookingDTO {

    private String booking_id;
    private String po_id;
    private String ps_id;
    public String po_username;
    public String ps_username;
    public String date_from;
    public String date_to;
    public String accepted;

    public FieldBookingDTO(String po_username, String ps_username, String date_from, String date_to) {
        this.po_username = po_username;
        this.ps_username = ps_username;
        this.date_from = date_from;
        this.date_to = date_to;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "po_username='" + po_username + '\'' +
                ", ps_username='" + ps_username + '\'' +
                ", date_from='" + date_from + '\'' +
                ", date_to='" + date_to + '\'' +
                '}';
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    public String getPo_id() {
        return po_id;
    }

    public void setPo_id(String po_id) {
        this.po_id = po_id;
    }

    public String getPs_id() {
        return ps_id;
    }

    public void setPs_id(String ps_id) {
        this.ps_id = ps_id;
    }

    public String getPo_username() {
        return po_username;
    }

    public String getPs_username() {
        return ps_username;
    }

    public String getDate_from() {
        return date_from;
    }

    public String getDate_to() {
        return date_to;
    }
}

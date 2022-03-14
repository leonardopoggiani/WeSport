package it.unipi.dsmt.dto;



public class FieldBookingDTO {

    private String booking_id;
    private String sport;

    public FieldBookingDTO(String id, String sport) {
        this.booking_id = id;
        this.sport = sport;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id='" + booking_id + '\'' +
                ", sport='" + sport + '\'' +
                '}';
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}

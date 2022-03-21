package it.unipi.dsmt.ejb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="booking")
public class FieldBooking implements Serializable {
    @Id
    @Column(name="ID")
    private Integer id;
    @Column(name="sport")
    private String sport;
    @Column(name="day")
    private Date day;
    @Column(name="start_hour")
    private Integer start_hour;
    @Column(name="end_hour")
    private Integer end_hour;
    @Column(name="booker")
    private Integer booker;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

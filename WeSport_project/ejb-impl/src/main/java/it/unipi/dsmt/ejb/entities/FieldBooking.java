package it.unipi.dsmt.ejb.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="booking")
public class FieldBooking implements Serializable {
    @Id
    @Column(name="ID")
    private Integer id;
    @Column(name="sport")
    private String sport;

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
}

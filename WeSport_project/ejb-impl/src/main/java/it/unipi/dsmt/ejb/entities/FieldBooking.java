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
    @Column(name="id_")
    private Integer id;
    @Column(name="petowner_id")
    private String po_id;
    @Column(name="petowner_username")
    private String po_us;
    @Column(name="petsitter_id")
    private String ps_id;
    @Column(name="petsitter_username")
    private String ps_us;
    @Column(name="from_date")
    private String from;
    @Column(name="to_date")
    private String to;
    @Column(name="pet")
    private String pet;
    @Column(name="status")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPo_id() {
        return po_id;
    }

    public void setPo_id(String po_id) {
        this.po_id = po_id;
    }

    public String getPo_us() {
        return po_us;
    }

    public void setPo_us(String po_us) {
        this.po_us = po_us;
    }

    public String getPs_id() {
        return ps_id;
    }

    public void setPs_id(String ps_id) {
        this.ps_id = ps_id;
    }

    public String getPs_us() {
        return ps_us;
    }

    public void setPs_us(String ps_us) {
        this.ps_us = ps_us;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

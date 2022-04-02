package it.unipi.dsmt.ejb;

import it.unipi.dsmt.dto.UserBookingDTO;

import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.BookingUserRemote;
import it.unipi.dsmt.interfaces.UserRemote;

import it.unipi.dsmt.interfaces.BookingUserRemote;

import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.BookingUserRemote;
import it.unipi.dsmt.interfaces.UserRemote;

import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.BookingUserRemote;
import it.unipi.dsmt.interfaces.UserRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserBookingEJB implements BookingUserRemote {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void setScore(Integer score) {

    }

    @Override
    public boolean updateScore(Integer userBookingID, Integer score) {
        return false;
    }

    @Override
    public UserBookingDTO displayUserBooking(Integer user_booking_id) {
        return null;
    }

    @Override
    public Integer displayUserBooking2(Integer user_id, Integer booking_id) {
        return null;
    }
}

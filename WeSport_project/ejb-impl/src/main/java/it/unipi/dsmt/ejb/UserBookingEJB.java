package it.unipi.dsmt.ejb;

import it.unipi.dsmt.dto.UserBookingDTO;
import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.BookingUserRemote;
import it.unipi.dsmt.interfaces.UserRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserBookingEJB implements BookingUserRemote {

    @PersistenceContext
    private EntityManager entityManager;

<<<<<<< HEAD
=======



}

>>>>>>> parent of 4bd11ab (Merge pull request #8 from leonardopoggiani/bookedEventServlet)

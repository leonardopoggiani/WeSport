package it.unipi.dsmt.ejb;

import it.unipi.dsmt.dto.UserBookingDTO;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.BookingUserRemote;
import it.unipi.dsmt.interfaces.UserRemote;
=======

import it.unipi.dsmt.interfaces.BookingUserRemote;

>>>>>>> parent of d110da1 (refactor)
=======
import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.BookingUserRemote;
import it.unipi.dsmt.interfaces.UserRemote;
>>>>>>> parent of 3132b2f (Merge branch 'bookedEventServlet' of https://github.com/leonardopoggiani/WeSport into bookedEventServlet)
=======
import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.interfaces.BookingUserRemote;
import it.unipi.dsmt.interfaces.UserRemote;
>>>>>>> parent of 82cbc86 (bookedevent)

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import java.sql.SQLException;
=======
import javax.persistence.criteria.CriteriaBuilder;
>>>>>>> parent of d110da1 (refactor)
=======
import java.sql.SQLException;
>>>>>>> parent of 3132b2f (Merge branch 'bookedEventServlet' of https://github.com/leonardopoggiani/WeSport into bookedEventServlet)
=======
import java.sql.SQLException;
>>>>>>> parent of 82cbc86 (bookedevent)
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserBookingEJB implements BookingUserRemote {

    @PersistenceContext
    private EntityManager entityManager;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======


<<<<<<< HEAD



}

<<<<<<< HEAD
>>>>>>> parent of 4bd11ab (Merge pull request #8 from leonardopoggiani/bookedEventServlet)
=======
        Query query = entityManager.createQuery(jpql);
        query.setParameter("user_id",user_id);
        query.setParameter("booking_id",booking_id);

        List<Integer> bookingList = query.getResultList();
        ArrayList<UserBookingDTO> result = new ArrayList<UserBookingDTO>();



        if (bookingList != null && !bookingList.isEmpty()) {
            userbookingid=(Integer) bookingList.get(0);

        }

        return userbookingid;


    }

    public boolean updateScore(Integer userBookingID, Integer score){
        String jpql = "UPDATE UserBooking SET score = 3 WHERE userBookingid = (:userBookingID)";

=======
>>>>>>> parent of 3132b2f (Merge branch 'bookedEventServlet' of https://github.com/leonardopoggiani/WeSport into bookedEventServlet)
=======
>>>>>>> parent of 82cbc86 (bookedevent)



}


>>>>>>> parent of d110da1 (refactor)
=======

>>>>>>> parent of c6a3122 (refactor)

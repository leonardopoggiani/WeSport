package it.unipi.dsmt.interfaces;

import it.unipi.dsmt.dto.UserBookingDTO;

import javax.ejb.Remote;
<<<<<<< HEAD
<<<<<<< HEAD
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
=======
import javax.persistence.criteria.CriteriaBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
>>>>>>> parent of d110da1 (refactor)
=======
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
>>>>>>> parent of 3132b2f (Merge branch 'bookedEventServlet' of https://github.com/leonardopoggiani/WeSport into bookedEventServlet)
import java.util.List;

@Remote
public interface BookingUserRemote {
<<<<<<< HEAD
<<<<<<< HEAD

=======
    void setScore(Integer score);
    boolean updateScore(Integer userBookingID, Integer score);
    UserBookingDTO displayUserBooking(Integer user_booking_id);
    Integer displayUserBooking2(Integer user_id, Integer booking_id);
>>>>>>> parent of d110da1 (refactor)
=======

>>>>>>> parent of 3132b2f (Merge branch 'bookedEventServlet' of https://github.com/leonardopoggiani/WeSport into bookedEventServlet)
}

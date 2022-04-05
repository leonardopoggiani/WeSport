package it.unipi.dsmt.ejb;

import it.unipi.dsmt.dto.UserBookingDTO;
import it.unipi.dsmt.interfaces.UserBookingRemote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserBookingEJB implements UserBookingRemote {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setScore(Integer score) {
        System.out.println("setScore");
    }

    @Override
    public UserBookingDTO displayUserBooking(Integer user_booking_id) {
        String jpql = "select b.userBookingid, b.bookingID, b.userID, b.score" +
                " from UserBooking b where b.userBookingid = :userBookingId";

        System.out.println("[LOG] user_booking_id: " + user_booking_id);

        Query query = entityManager.createQuery(jpql);
        query.setParameter("userBookingId", user_booking_id);

        List<Object[]> bookingList = query.getResultList();
        ArrayList<UserBookingDTO> result = new ArrayList<>();

        if (bookingList != null && !bookingList.isEmpty()) {
            for (Object[] booking : bookingList) {
                UserBookingDTO dto = new UserBookingDTO();
                dto.setUserbooking_id((Integer) booking[0]);
                dto.setBooking_id((Integer) booking[1]);
                dto.setUser_id((Integer) booking[2]);
                dto.setScore((Integer) booking[3]);

                result.add(dto);
            }
        }
        UserBookingDTO userBookingDTO=result.get(0);
        return userBookingDTO;
    }

    public Integer displayUserBooking2(Integer user_id, Integer booking_id) {
        String jpql = "select ub.userBookingid" +
                " from UserBooking ub where ub.userID = :user_id and ub.bookingID = :booking_id";

        Integer userbookingid = -1;

        System.out.println("[LOG] user_booking_id: " + booking_id);

        Query query = entityManager.createQuery(jpql);
        query.setParameter("user_id",user_id);
        query.setParameter("booking_id",booking_id);

        List<Integer> bookingList = query.getResultList();
        ArrayList<UserBookingDTO> result = new ArrayList<UserBookingDTO>();

        if (bookingList != null && !bookingList.isEmpty()) {
            userbookingid = bookingList.get(0);

        }
        return userbookingid;
    }

    public boolean updateScore(Integer userBookingID, Integer score){
        String jpql = "UPDATE UserBooking SET score = :score WHERE userBookingid = (:userBookingID)";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("score",score);
        query.setParameter("userBookingID",userBookingID);

        int ris = query.executeUpdate();
        return true;
    }

}

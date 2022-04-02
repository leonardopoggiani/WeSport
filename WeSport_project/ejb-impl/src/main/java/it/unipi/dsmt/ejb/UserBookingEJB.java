package it.unipi.dsmt.ejb;


import it.unipi.dsmt.dto.FieldBookingDTO;
import it.unipi.dsmt.dto.UserBookingDTO;

import it.unipi.dsmt.interfaces.BookingUserRemote;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.sql.*;


import java.util.Date;
import java.util.List;

@Stateless
public class UserBookingEJB implements BookingUserRemote {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setScore(Integer score) {
        System.out.println("setScore");
    }

    @Override
    public UserBookingDTO displayUserBooking(Integer user_booking_id) {
        String jpql = "select b.userBookingid,b.bookingID,b.userID,b.score" +
                " from UserBooking b where b.userBookingid = "+user_booking_id;

        System.out.println("[LOG] user_booking_id: " + user_booking_id);

        Query query = entityManager.createQuery(jpql);

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
                " from UserBooking ub where ub.userID = :user_id and ub.bookingID= :booking_id";

        Integer userbookingid=-1;

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


        Query query = entityManager.createQuery(jpql);
        query.setParameter("userBookingID",userBookingID);

        int ris=query.executeUpdate();
        return true;
    }

}



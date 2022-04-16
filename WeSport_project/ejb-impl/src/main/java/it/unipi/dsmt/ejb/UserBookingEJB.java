package it.unipi.dsmt.ejb;

import it.unipi.dsmt.dto.UserBookingDTO;
import it.unipi.dsmt.ejb.entities.FieldBooking;
import it.unipi.dsmt.ejb.entities.UserBooking;
import it.unipi.dsmt.interfaces.UserBookingRemote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
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

    public double retrieveScore(String username){

        String jpqlToRetrieveID = "select u.id" +
                                " from User u " +
                                " where u.username = :username ";

        Query queryToRetrieveID = entityManager.createQuery(jpqlToRetrieveID);
        queryToRetrieveID.setParameter("username", username);

        List<Integer> idList = queryToRetrieveID.getResultList();
        ArrayList<UserBookingDTO> result = new ArrayList<UserBookingDTO>();
        Integer user_id = 0;

        if (idList != null && !idList.isEmpty()) {
            user_id = idList.get(0);
        }

        String jpql = "SELECT SUM(ub.score) AS somma_voti, COUNT(ub.userID) AS numero_voti FROM UserBooking ub WHERE ub.userID = :userID";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("userID", user_id);

        List<Object[]> scoreList = query.getResultList();

        if (scoreList != null && !scoreList.isEmpty()) {
            for(Object[] scoreInfo: scoreList){
                if((Long) scoreInfo[1] != 0) {
                    return (double) (Long) scoreInfo[0] / (double) (Long) scoreInfo[1];
                }
            }
        }

        return 0;
    }

    @Override
    public void deleteBooking(Integer id_user_booking) {
        UserBooking ub = entityManager.find(UserBooking.class, id_user_booking);
        entityManager.remove(ub);
    }

    @Override
    public void insertNewBooking(Integer[] users, Integer idBooking) {
        for (int i=0; i<users.length; i++){
            UserBooking toPersist = new UserBooking();
            toPersist.setUserID(users[i]);
            toPersist.setBookingID(idBooking);
            entityManager.persist(toPersist);
        }
    }

    @Override
    public Integer[] retrieveRowsRelatedtoABooking(int bookingID) {
        String jpql = "select ub.userBookingid from UserBooking ub where ub.bookingID=?1 ";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("1", bookingID);

        List<Object> res = query.getResultList();
        Integer[] ret = null;
        if (res.isEmpty()) {
            System.out.println("[LOG] Nothing was found");
        } else {
            ret = new Integer[res.size()];
            for (int i=0; i<res.size(); i++){
                System.out.println(res.get(i));
                int id = Integer.parseInt(res.get(i).toString());
                ret[i] = id;
            }
        }

        return ret;
    }

}

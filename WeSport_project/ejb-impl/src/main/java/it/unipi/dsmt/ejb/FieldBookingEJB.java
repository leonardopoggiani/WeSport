package it.unipi.dsmt.ejb;

import it.unipi.dsmt.dto.FieldBookingDTO;
import it.unipi.dsmt.interfaces.FieldBookingRemote;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class FieldBookingEJB implements FieldBookingRemote {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertBooking(Integer id,String sport) throws SQLException {
        FieldBookingDTO toPersist = new FieldBookingDTO();
        toPersist.setBooking_id(id);
        toPersist.setSport(sport);
        entityManager.persist(toPersist);
    }

    @Override
    public ArrayList<FieldBookingDTO> displayBooking(String username) {
        String jpql = "select b.id, b.sport, b.day, b.start_hour, b.end_hour, b.booker" +
                " from FieldBooking b where lower(b.booker) = lower(:username)";

        System.out.println("[LOG] displayBooking: " + username);

        Query query = entityManager.createQuery(jpql);
        query.setParameter("username", username);

        List<Object[]> bookingList = query.getResultList();
        ArrayList<FieldBookingDTO> result = new ArrayList<>();

        if (bookingList != null && !bookingList.isEmpty()) {
            for(Object[] booking: bookingList){
                FieldBookingDTO dto = new FieldBookingDTO();
                dto.setBooking_id((Integer) booking[0]);
                dto.setSport((String) booking[1]);
                dto.setDay((Date) booking[2]);
                dto.setStart_hour((Integer) booking[3]);
                dto.setEnd_hour((Integer) booking[4]);
                dto.setBooker((String) booking[5]);

                result.add(dto);
            }
        }

        return result;
    }

    @Override
    public ArrayList<FieldBookingDTO> displayBookingForSport(String sport) {
        String jpql = "select b.id, b.sport, b.day, b.start_hour, b.end_hour, b.booker" +
                " from FieldBooking b where lower(b.sport) = lower(:sport)";

        System.out.println("[LOG] displayBookingSport: " + sport);

        Query query = entityManager.createQuery(jpql);
        query.setParameter("sport", sport);

        List<Object[]> bookingList = query.getResultList();
        ArrayList<FieldBookingDTO> result = new ArrayList<>();

        if (bookingList != null && !bookingList.isEmpty()) {
            for(Object[] booking: bookingList){
                FieldBookingDTO dto = new FieldBookingDTO();
                dto.setBooking_id((Integer) booking[0]);
                dto.setSport((String) booking[1]);
                dto.setDay((Date) booking[2]);
                dto.setStart_hour((Integer) booking[3]);
                dto.setEnd_hour((Integer) booking[4]);
                dto.setBooker((String) booking[5]);

                result.add(dto);
            }
        }

        return result;
    }

}

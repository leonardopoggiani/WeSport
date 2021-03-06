package it.unipi.dsmt.ejb;

import it.unipi.dsmt.dto.FieldBookingDTO;
import it.unipi.dsmt.dto.UserDTO;
import it.unipi.dsmt.ejb.entities.FieldBooking;
import it.unipi.dsmt.ejb.entities.User;
import it.unipi.dsmt.interfaces.FieldBookingRemote;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;
import java.sql.SQLException;

@Stateless
public class FieldBookingEJB implements FieldBookingRemote {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertBooking(String sport, Date date, Integer start_hour, Integer end_hour, Integer booker) throws SQLException {
        FieldBooking toPersist = new FieldBooking();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        toPersist.setSport(sport);
        toPersist.setDay(sqlDate);
        toPersist.setStart_hour(start_hour);
        toPersist.setEnd_hour(end_hour);
        toPersist.setBooker(booker);

        entityManager.persist(toPersist);
    }

    @Override
    public ArrayList<FieldBookingDTO> displayBooking(Integer user_id) {
        String jpql = "select b.id, b.sport, b.day, b.start_hour, b.end_hour, b.booker" +
                " from FieldBooking b where b.booker = :user_id";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("user_id", user_id);

        List<Object[]> bookingList = query.getResultList();
        ArrayList<FieldBookingDTO> result = new ArrayList<>();

        if (bookingList != null && !bookingList.isEmpty()) {
            for (Object[] booking : bookingList) {
                FieldBookingDTO dto = new FieldBookingDTO();
                dto.setBooking_id((Integer) booking[0]);
                dto.setSport((String) booking[1]);
                dto.setDay((Date) booking[2]);
                dto.setStart_hour((Integer) booking[3]);
                dto.setEnd_hour((Integer) booking[4]);
                dto.setBooker((Integer) booking[5]);

                result.add(dto);
            }
        }

        return result;
    }

    @Override
    public ArrayList<FieldBookingDTO> displayBookingForSport(String sport) {
        String jpql = "select b.id, b.sport, b.day, b.start_hour, b.end_hour, b.booker" +
                " from FieldBooking b where lower(b.sport) = lower(:sport)";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("sport", sport);

        List<Object[]> bookingList = query.getResultList();
        ArrayList<FieldBookingDTO> result = new ArrayList<>();

        if (bookingList != null && !bookingList.isEmpty()) {
            for (Object[] booking : bookingList) {
                FieldBookingDTO dto = new FieldBookingDTO();
                dto.setBooking_id((Integer) booking[0]);
                dto.setSport((String) booking[1]);
                dto.setDay((Date) booking[2]);
                dto.setStart_hour((Integer) booking[3]);
                dto.setEnd_hour((Integer) booking[4]);
                dto.setBooker((Integer) booking[5]);

                result.add(dto);
            }
        }

        return result;
    }

    @Override
    public ArrayList<FieldBookingDTO> displayBookingByFilter(Integer bookerID, String sport) {
        ArrayList<FieldBookingDTO> ret = new ArrayList<>();
        if (bookerID == null && sport == null) {
            return ret;
        }
        else if(sport==null){
            ret = displayBooking(bookerID);
            return ret;
        }
        else if(bookerID==null){
            ret = displayBookingForSport(sport);
            return ret;
        }
        else {
            String jpql = "select b.id, b.sport, b.day, b.start_hour, b.end_hour, b.booker from FieldBooking b where b.booker=?1 and b.sport=?2";
            Query query = entityManager.createQuery(jpql);
            query.setParameter(1, bookerID);
            query.setParameter(2, sport);

            List<Object[]> bookingList = query.getResultList();

            if (bookingList != null && !bookingList.isEmpty()) {
                for (Object[] booking : bookingList) {
                    FieldBookingDTO dto = new FieldBookingDTO();
                    dto.setBooking_id((Integer) booking[0]);
                    dto.setSport((String) booking[1]);
                    dto.setDay((Date) booking[2]);
                    dto.setStart_hour((Integer) booking[3]);
                    dto.setEnd_hour((Integer) booking[4]);
                    dto.setBooker((Integer) booking[5]);

                    ret.add(dto);
                }

            }
        }

        return ret;

    }

    @Override
    public int lastBookingInserted() {
        String jpql = "select max(b.id) from FieldBooking b";
        Query query = entityManager.createQuery(jpql);
        List<Object> res = query.getResultList();
        return Integer.parseInt(res.get(0).toString());
    }

    @Override
    public ArrayList<FieldBookingDTO> displayBookingNotExpired(String sport) {
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);

        String jpql = "select b.id, b.sport, b.day, b.start_hour, b.end_hour, b.booker" +
                " from FieldBooking b where lower(b.sport) = lower(:sport) and b.day > :date";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("sport", sport);
        query.setParameter("date", date);

        List<Object[]> bookingList = query.getResultList();
        ArrayList<FieldBookingDTO> result = new ArrayList<>();

        if (bookingList != null && !bookingList.isEmpty()) {
            for (Object[] booking : bookingList) {
                FieldBookingDTO dto = new FieldBookingDTO();
                dto.setBooking_id((Integer) booking[0]);
                dto.setSport((String) booking[1]);
                dto.setDay((Date) booking[2]);
                dto.setStart_hour((Integer) booking[3]);
                dto.setEnd_hour((Integer) booking[4]);
                dto.setBooker((Integer) booking[5]);

                result.add(dto);
            }
        }

        return result;
    }

    @Override
    public ArrayList<FieldBookingDTO> displayBookingExpired(String sport) {
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);

        String jpql = "select b.id, b.sport, b.day, b.start_hour, b.end_hour, b.booker" +
                " from FieldBooking b where lower(b.sport) = lower(:sport) and b.day < :date";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("sport", sport);
        query.setParameter("date", date);

        List<Object[]> bookingList = query.getResultList();
        ArrayList<FieldBookingDTO> result = new ArrayList<>();

        if (bookingList != null && !bookingList.isEmpty()) {
            for (Object[] booking : bookingList) {
                FieldBookingDTO dto = new FieldBookingDTO();
                dto.setBooking_id((Integer) booking[0]);
                dto.setSport((String) booking[1]);
                dto.setDay((Date) booking[2]);
                dto.setStart_hour((Integer) booking[3]);
                dto.setEnd_hour((Integer) booking[4]);
                dto.setBooker((Integer) booking[5]);

                result.add(dto);
            }
        }

        return result;
    }

    @Override
    public boolean[] displayBusyDaysForMonth(String sport, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) +1 ;
        int year = cal.get(Calendar.YEAR);
        Query query = entityManager.createQuery("select SUBSTRING(b.day, 9, 2), COUNT(b)" +
                        " from FieldBooking b  WHERE SUBSTRING(b.day, 6, 2) = ?2 AND b.sport = ?1 " +
                         "AND SUBSTRING(b.day, 1, 4) = ?3 GROUP BY b.day ORDER BY b.day ASC ");
        query.setParameter(1, sport);
        query.setParameter(2, month);
        query.setParameter(3,year);

        List<Object[]> res = query.getResultList();

        boolean[] freeDays = new boolean[cal.getActualMaximum(Calendar.DAY_OF_MONTH)];

        if (res.isEmpty()) {
            Arrays.fill(freeDays, true);
        } else {
            for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++)
                for (Object[] booking : res) {
                    int day = Integer.parseInt(booking[0].toString());
                    int bookedSlots = Integer.parseInt(booking[1].toString());
                    if ((day==(i+1)) && bookedSlots==12) {
                        freeDays[i] = false;
                        break;
                    } else {
                        freeDays[i] = true;
                    }

                }
        }

        return freeDays;
    }

    public boolean[] displayBusyTimeslotForDay(String sport, Date date ){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DATE);
        Query query = entityManager.createQuery("select b.start_hour" +
                " from FieldBooking b  WHERE SUBSTRING(b.day, 9, 2) = ?2 AND b.sport = ?1 " +
                "ORDER BY b.start_hour ASC ");
        query.setParameter(1, sport);
        query.setParameter(2, day);

        List<Object> res = query.getResultList();

        boolean[] freeTimeslot = new boolean[12];
        if (res.isEmpty()) {
            Arrays.fill(freeTimeslot, true);
        } else {
            for (int i = 0; i < 12; i++)
                for (int j=0; j<res.size(); j++) {
                    System.out.println(res.get(j).toString());
                    int timeslot = Integer.parseInt(res.get(j).toString());
                    if (timeslot==(i+7)) {
                        freeTimeslot[i] = false;
                        break;
                    } else {
                        freeTimeslot[i] = true;
                    }
                }
        }

        return freeTimeslot;
    }

    public void deleteBooking (Integer idBooking){
        FieldBooking booking = entityManager.find(FieldBooking.class, idBooking );
        entityManager.remove(booking);
    }

}

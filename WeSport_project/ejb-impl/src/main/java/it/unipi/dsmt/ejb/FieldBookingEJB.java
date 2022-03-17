package it.unipi.dsmt.ejb;

import it.unipi.dsmt.dto.FieldBookingDTO;
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
    public void insertBooking(Integer id, String sport) throws SQLException {
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
            for (Object[] booking : bookingList) {
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
            for (Object[] booking : bookingList) {
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
    public ArrayList<FieldBookingDTO> displayBookingNotExpired(String sport) {
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);

        String jpql = "select b.id, b.sport, b.day, b.start_hour, b.end_hour, b.booker" +
                " from FieldBooking b where lower(b.sport) = lower(:sport) and b.day > :date";

        System.out.println("[LOG] displayBooking: " + sport);
        System.out.println("[LOG] date: " + date);

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
                dto.setBooker((String) booking[5]);

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

        System.out.println("[LOG] displayBooking: " + sport);
        System.out.println("[LOG] date: " + date);

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
                dto.setBooker((String) booking[5]);

                result.add(dto);
            }
        }

        return result;
    }

    @Override
    public boolean[] displayBusyDaysForMonth(String sport, Date date) {
        Query query = entityManager.createNativeQuery(
                "SELECT * " +
                        "FROM wesport.booking " +
                        "WHERE sport = ?1 " +
                        "AND MONTH(day) = ?2 " +
                        "AND YEAR(day) = ?3 " +
                        "ORDER BY day");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        System.out.println("date: " + date);
        System.out.println("sport: " + sport);
        System.out.println("calendar.get(Calendar.MONTH): " + (calendar.get(Calendar.MONTH) + 1));
        System.out.println("calendar.get(Calendar.YEAR): " + calendar.get(Calendar.YEAR));

        query.setParameter(1, sport);
        query.setParameter(2, calendar.get(Calendar.MONTH) + 1);
        query.setParameter(3, calendar.get(Calendar.YEAR));

        List<Object[]> bookingList = query.getResultList();

        System.out.println("[LOG] prenotazioni del mese: " + bookingList.size());
        System.out.println("[LOG] giorni del mese: " +  calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        // in questo modo ho trovato le prenotazioni di questo mese in questo anno
        boolean[] freeDays = new boolean[calendar.getActualMaximum(Calendar.DAY_OF_MONTH)];

        if (bookingList.isEmpty()) {
            System.out.println("[LOG] Nessuna prenotazione trovata");
            Arrays.fill(freeDays, true);
        } else {
            for (int i = 0; i < calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++)
                for (Object[] booking : bookingList) {
                    Calendar calendarBooking = Calendar.getInstance();
                    calendarBooking.setTime((Date) booking[2]);

                    if (i == (calendarBooking.get(Calendar.DAY_OF_MONTH) - 1)) {
                        freeDays[i] = false;
                        break;
                    } else {
                        freeDays[i] = true;
                    }

                }
        }

        return freeDays;
    }
}

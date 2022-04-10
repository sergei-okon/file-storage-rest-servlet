package ua.com.okonsergei.repository.db.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.com.okonsergei.repository.EventRepository;
import ua.com.okonsergei.repository.entity.Event;
import ua.com.okonsergei.utils.HibernateUtil;

import java.time.LocalDateTime;
import java.util.List;

public class EventEntityRepositoryImpl implements EventRepository {
    @Override
    public Event findById(Long id) {
        Event event = null;
        if (id != null) {
            try (Session session = HibernateUtil.openSession()) {
                event = session.get(Event.class, id);
            }
        }
        return event;
    }


    @Override
    public List<Event> findAll() {
        List<Event> events;
        try (Session session = HibernateUtil.openSession()) {
            events = session.createQuery("From Event ", Event.class).list();
        }
        return events;
    }

    @Override
    public Event save(Event event) {
        try (Session session = HibernateUtil.openSession()) {
            Transaction transaction = session.beginTransaction();
            Long eventId = (Long) session.save(event);
            event.setId(eventId);
            transaction.commit();
        }
        return event;
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.openSession()) {
            Transaction transaction = session.beginTransaction();

            Event event = session.get(Event.class, id);
            if (event == null) {
                System.out.println("Unable to delete Event from database. Event with id " + id + " not found");
            } else {
                session.delete(event);
                System.out.println("Deleted Event by id " + id);
            }
            transaction.commit();
        }
    }

    @Override
    public Long update(Event event) {
        try (Session session = HibernateUtil.openSession()) {
            Transaction transaction = session.beginTransaction();
            Event eventForUpdate = session.get(Event.class, event.getId());

            if (eventForUpdate.getId() == null) {
                System.out.println("Unable to update Event. Event not found");
            } else {
                eventForUpdate.setFile(event.getFile());
                eventForUpdate.setUser(event.getUser());
                eventForUpdate.setUpdated(LocalDateTime.now());

                session.update(eventForUpdate);
                System.out.println("Update Event with id " + eventForUpdate.getId());
            }
            transaction.commit();
            return eventForUpdate.getId();
        }
    }
}

package ua.com.okonsergei.repository.db.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.com.okonsergei.repository.UserRepository;
import ua.com.okonsergei.repository.entity.User;
import ua.com.okonsergei.utils.HibernateUtil;

import java.util.List;

public class UserEntityRepositoryImpl implements UserRepository {
    @Override
    public User findById(Long id) {
        User user;
        try (Session session = HibernateUtil.openSession()) {
            user = session.get(User.class, id);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users;
        try (Session session = HibernateUtil.openSession()) {
            users = session.createQuery("From User ", User.class).list();
        }
        return users;
    }

    @Override
    public User save(User user) {
        try (Session session = HibernateUtil.openSession()) {
            Transaction transaction = session.beginTransaction();
            Long userId = (Long) session.save(user);
            user.setId(userId);
            transaction.commit();
        }
        return user;
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = session.get(User.class, id);
            if (user == null) {
                System.out.println("Unable to delete User from database. File with id " + id + " not found");
            } else {
                session.delete(user);
                System.out.println("Deleted User by id " + id);
            }
            transaction.commit();
        }
    }

    @Override
    public Long update(User user) {
        try (Session session = HibernateUtil.openSession()) {
            Transaction transaction = session.beginTransaction();
            User userForUpdate = session.get(User.class, user.getId());

            if (userForUpdate.getId() == null) {
                System.out.println("Unable to update User. User not found");
            } else {
                userForUpdate.setName(user.getName());
                userForUpdate.setEvents(user.getEvents());

                session.update(userForUpdate);
                System.out.println("Update User with id " + userForUpdate.getId());
            }
            transaction.commit();
            return userForUpdate.getId();
        }
    }
}
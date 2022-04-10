package ua.com.okonsergei.repository.db.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.com.okonsergei.repository.FileRepository;
import ua.com.okonsergei.repository.entity.File;
import ua.com.okonsergei.utils.HibernateUtil;

import java.util.List;

public class FileEntityRepositoryImpl implements FileRepository {

    @Override
    public File findById(Long id) {
        File file;
        try (Session session = HibernateUtil.openSession()) {
            file = session.get(File.class, id);
        }
        return file;
    }

    @Override
    public List<File> findAll() {
        List<File> files;
        try (Session session = HibernateUtil.openSession()) {
            files = session.createQuery("From File", File.class).list();
        }
        return files;
    }

    @Override
    public File save(File file) {
        try (Session session = HibernateUtil.openSession()) {
            Transaction transaction = session.beginTransaction();
            Long fileId = (Long) session.save(file);
            file.setId(fileId);
            transaction.commit();
        }
        return file;
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.openSession()) {
            Transaction transaction = session.beginTransaction();

            File file = session.get(File.class, id);
            if (file == null) {
                System.out.println("Unable to delete File from database. File with id " + id + " not found");
            } else {
                session.delete(file);
                System.out.println("Deleted Post by id " + id);
            }
            transaction.commit();
        }
    }

    @Override
    public Long update(File file) {
        try (Session session = HibernateUtil.openSession()) {
            Transaction transaction = session.beginTransaction();
            File fileForUpdate = session.get(File.class, file.getId());

            if (fileForUpdate.getId() == null) {
                System.out.println("Unable to update File. File not found");
            } else {
                fileForUpdate.setFileName(file.getFileName());
                fileForUpdate.setPath(file.getPath());

                session.update(fileForUpdate);
                System.out.println("Update File with id " + fileForUpdate.getId());
            }
            transaction.commit();
            return fileForUpdate.getId();
        }
    }
}
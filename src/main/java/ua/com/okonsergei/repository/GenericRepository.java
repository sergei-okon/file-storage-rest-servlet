package ua.com.okonsergei.repository;


import java.util.List;

public interface GenericRepository<T, ID> {

    T findById(ID id);

    List<T> findAll();

    T save(T t);

    void deleteById(ID id);

    Long update(T t);

}

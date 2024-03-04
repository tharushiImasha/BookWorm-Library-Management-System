package lk.ijse.repository;

import java.util.List;

public interface CrudRepository<T> extends SuperRepository {
     boolean save(T entity);
     boolean update(T entity);
     boolean delete(T entity);
     T search(String id);
     List<T> getAll();
}

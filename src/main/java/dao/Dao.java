package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(long id) throws SQLException;

    List<T> getAll() throws SQLException;

    T save(T t) throws SQLException;

    T update(T t) throws SQLException;

    void deleteById(Long id) throws SQLException;
}

package tikape.runko.database;

import java.sql.*;
import java.util.*;

public interface Dao<T, K, N, M> {

    T findOne(K key) throws SQLException;

    List<T> findAll() throws SQLException;
    
    List<T> findAllIn(K key) throws SQLException; 
    
    void addNew(K key, N name, M message) throws SQLException;

    void delete(K key) throws SQLException;
}

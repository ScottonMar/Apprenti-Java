package org.example.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A generic interface for mapping a JDBC ResultSet row to a model object.
 *
 * @param <T> the type of the model object
 */
public interface RowMapper<T> {

    /**
     * Maps the current row of the given ResultSet to an object of type T.
     *
     * @param rs the ResultSet, positioned at the current row
     * @return the mapped object of type T
     * @throws SQLException if a database access error occurs
     */
    T mapRow(ResultSet rs) throws SQLException;

}

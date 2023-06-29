package org.example.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public interface DatamartReader {
    Statement setConnect() throws SQLException;

    List<String> getLocations(String tableName, Statement statement, LocalDate from, LocalDate to) throws SQLException;
}

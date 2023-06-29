package org.example.controller;

import org.example.model.SqliteReader;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class Controller {
    public void apiControl() {
        new WebService(new Controller()).webService();
    }

    public List<String> getMinLoc(LocalDate fromDate, LocalDate toDate) throws SQLException {
        SqliteReader sqliteData = new SqliteReader();
        Statement statement = sqliteData.setConnect();
        return sqliteData.getLocations("temp_min", statement, fromDate, toDate);
    }

    public List<String> getMaxLoc(LocalDate fromDate, LocalDate toDate) throws SQLException {
        SqliteReader sqliteData = new SqliteReader();
        Statement statement = sqliteData.setConnect();
        return sqliteData.getLocations("temp_max", statement, fromDate, toDate);
    }
}

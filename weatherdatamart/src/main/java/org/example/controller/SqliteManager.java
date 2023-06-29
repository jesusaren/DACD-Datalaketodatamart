package org.example.controller;

import org.json.JSONObject;

import java.sql.*;
import java.text.ParseException;
import java.util.List;

public class SqliteManager implements DatamartManager {
    public void setConnect(List<JSONObject> min, List<JSONObject> max) throws ParseException {
        String url = "database/datamart.db";
        String dbPath = "jdbc:sqlite:" + url;
        try (Connection connection = connect(dbPath)) {
            Statement statement = connection.createStatement();
            createMinTable(statement);
            createMaxTable(statement);
            insertMin(min, connection);
            insertMax(max, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection connect(String dbPath) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbPath);
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void createMinTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS temp_min (" +
                "date TEXT NOT NULL," +
                "time TEXT NOT NULL," +
                "place TEXT NOT NULL," +
                "station TEXT NOT NULL," +
                "value REAL NOT NULL" +
                ");");
        statement.executeUpdate("DELETE FROM temp_min");
    }

    private void createMaxTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS temp_max (" +
                "date TEXT NOT NULL," +
                "time TEXT NOT NULL," +
                "place TEXT NOT NULL," +
                "station TEXT NOT NULL," +
                "value REAL NOT NULL" +
                ");");
        statement.executeUpdate("DELETE FROM temp_max");
    }

    private void insertMin(List<JSONObject> minimums, Connection connection) throws ParseException {
        new SqliteStore().insertWeatherData("temp_min", minimums, connection);
    }

    private void insertMax(List<JSONObject> maximums, Connection connection) throws ParseException {
        new SqliteStore().insertWeatherData("temp_max", maximums, connection);
    }
}



package org.example.controller;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SqliteStore {
    public void insertWeatherData(String tableName, List<JSONObject> list, Connection connection) throws ParseException {
        String formatSqlite = "INSERT INTO " + tableName + " (date,time,place,station,value) VALUES (?, ?, ?, ?, ?)";
        for (
                JSONObject weather : list) {
            String fullDate = weather.getString("date");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = format.parse(fullDate);
            DateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat newTimeFormat = new SimpleDateFormat("HH:mm:ss");
            String newDate = newDateFormat.format(date);
            String time = newTimeFormat.format(date);
            try (
                    PreparedStatement pstm = connection.prepareStatement(formatSqlite)) {
                pstm.setString(1, newDate);
                pstm.setString(2, time);
                pstm.setString(3, weather.getString("location"));
                pstm.setString(4, weather.getString("station"));
                pstm.setDouble(5, weather.getDouble("tempMin"));
                pstm.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

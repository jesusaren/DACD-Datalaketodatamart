package org.example.controller;

import org.example.model.Weather;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AemetSensor implements WeatherSensor {
    public String getData(String apiKey, String url) throws IOException {
        return Jsoup.connect(url)
                .timeout(10000)
                .ignoreContentType(true)
                .maxBodySize(0)
                .header("accept", "application/json")
                .header("api_key", apiKey)
                .method(Connection.Method.GET)
                .execute()
                .body();
    }

    public List<Weather> readData(JSONArray jsonArray) {
        List<Weather> events = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject event = jsonArray.getJSONObject(i);
            if (event.has("tamin") && event.has("tamax")) {
                double lon = event.getDouble("lon");
                double lat = event.getDouble("lat");
                if (lon >= -16 && lon <= -15) {
                    if (lat >= 27.5 && lat <= 28.4) {
                        events.add(new Weather(event.getString("idema"), event.getString("ubi"),
                                event.getString("fint"), event.getDouble("tamin"), event.getDouble("tamax")));
                    }
                }
            }
        }
        return events;
    }
}



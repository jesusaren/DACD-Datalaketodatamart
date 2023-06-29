package org.example.controller;

import org.example.model.Weather;
import org.json.JSONArray;

import java.io.IOException;
import java.util.List;

public interface WeatherSensor {
    String getData(String apiKey, String url) throws IOException;

    List<Weather> readData(JSONArray jsonArray);
}

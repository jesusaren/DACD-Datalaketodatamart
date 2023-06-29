package org.example.controller;

import org.example.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class Controller implements WeatherSensor, DatalakeWriter {
    public void controlFeeder(String apiKey) throws Exception {
        String aemet = getData(apiKey, "https://opendata.aemet.es/opendata/api/observacion/convencional/todas");
        JSONObject json = new JSONObject(aemet);
        String urlData = json.getString("datos");
        String data = getData(apiKey, urlData);
        JSONArray jsonArray = new JSONArray(data);
        List<Weather> events = readData(jsonArray);
        storeData(events);
    }

    @Override
    public String getData(String apiKey, String url) throws IOException {
        return new AemetSensor().getData(apiKey, url);
    }

    @Override
    public List<Weather> readData(JSONArray jsonArray) {
        return new AemetSensor().readData(jsonArray);
    }

    @Override
    public void storeData(List<Weather> events) throws IOException {
        new FileWriter().storeData(events);
    }


}

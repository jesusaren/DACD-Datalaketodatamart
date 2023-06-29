package org.example.model;

import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FileReader implements DatalakeReader {
    private final List<JSONObject> maxTemperatures = new ArrayList<>();
    private final List<JSONObject> minTemperatures = new ArrayList<>();

    public List<JSONObject> getMaxTemperatures() throws IOException {
        reader();
        return maxTemperatures;
    }

    public List<JSONObject> getMinTemperatures() throws IOException {
        reader();
        return minTemperatures;
    }

    private void reader() throws IOException {
        String uri = "datalake";
        File directory = new File(uri);
        String[] files = directory.list();
        assert files != null;
        for (String name : files) {
            List<JSONObject> events = new ArrayList<>();
            File day = new File(uri + "/" + name);
            FileInputStream inputStream = new FileInputStream(day);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                JSONObject jsonObject = new JSONObject(line);
                events.add(jsonObject);
            }
            filterMax(events);
            filterMin(events);
            reader.close();
        }
    }

    private void filterMin(List<JSONObject> events) {
        JSONObject min = events.stream().
                reduce((o1, o2) -> Double.compare(o1.getDouble("tempMin"), o2.getDouble("tempMin")) < 0 ? o1 : o2)
                .orElse(null);
        minTemperatures.add(min);
    }

    private void filterMax(List<JSONObject> events) {
        JSONObject max = events.stream().
                reduce((o1, o2) -> Double.compare(o1.getDouble("tempMax"), o2.getDouble("tempMax")) > 0 ? o1 : o2)
                .orElse(null);
        maxTemperatures.add(max);
    }
}

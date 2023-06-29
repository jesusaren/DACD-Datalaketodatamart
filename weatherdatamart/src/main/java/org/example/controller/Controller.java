package org.example.controller;

import org.example.model.DatalakeReader;
import org.example.model.FileReader;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Controller implements DatalakeReader, DatamartManager {
    public void datamartControl() throws IOException, ParseException {
        List<JSONObject> max = getMaxTemperatures();
        List<JSONObject> min = getMinTemperatures();
        setConnect(min, max);

    }

    @Override
    public void setConnect(List<JSONObject> min, List<JSONObject> max) throws ParseException {
        new SqliteManager().setConnect(min, max);

    }

    @Override
    public List<JSONObject> getMaxTemperatures() throws IOException {
        return new FileReader().getMaxTemperatures();
    }

    @Override
    public List<JSONObject> getMinTemperatures() throws IOException {
        return new FileReader().getMinTemperatures();
    }
}

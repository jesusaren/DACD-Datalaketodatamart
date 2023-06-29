package org.example.controller;

import org.json.JSONObject;

import java.text.ParseException;
import java.util.List;

public interface DatamartManager {
    void setConnect(List<JSONObject> min, List<JSONObject> max) throws ParseException;
}

package org.example.model;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;


public interface DatalakeReader {
    List<JSONObject> getMaxTemperatures() throws IOException;

    List<JSONObject> getMinTemperatures() throws IOException;
}

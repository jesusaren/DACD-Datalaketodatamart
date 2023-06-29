package org.example.model;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface DatalakeWriter {
    void storeData(List<Weather> events) throws IOException, ParseException;

}

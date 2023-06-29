package org.example.model;

public class Weather {
    private final String station;
    private final String location;
    private final String date;
    private final double tempMin;
    private final double tempMax;

    public Weather(String station, String location, String date, double tempMin, double tempMax) {
        this.station = station;
        this.location = location;
        this.date = date;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public String getDate() {
        return date;
    }

}

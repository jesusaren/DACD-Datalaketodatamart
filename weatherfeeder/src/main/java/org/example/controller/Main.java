package org.example.controller;

import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXN1c2FyZW5mYWxAZ21haWwuY29tIiwianRpIjoiNDY4NzY4NDktMTM0My00ZTRhLWJmZjEtM2Q3NGQ2Mzc3Y2M5IiwiaXNzIjoiQUVNRVQiLCJpYXQiOjE2ODc5NzY1NTAsInVzZXJJZCI6IjQ2ODc2ODQ5LTEzNDMtNGU0YS1iZmYxLTNkNzRkNjM3N2NjOSIsInJvbGUiOiIifQ._poiMb7PMsr8YYV62XJAVPmDebSWv2MLg3ea-6MBG5s";
        Timer timer = new Timer();
        FeederTimerTask task = new FeederTimerTask(apiKey);
        timer.scheduleAtFixedRate(task, 0, 3600000);
    }
}

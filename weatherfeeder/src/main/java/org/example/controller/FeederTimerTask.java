package org.example.controller;

import java.util.TimerTask;

public class FeederTimerTask extends TimerTask {
    private final String apiKey;

    public FeederTimerTask(String apiKey) {
        this.apiKey = apiKey;
    }

    public void run() {
        try {
            new Controller().controlFeeder(apiKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package org.example.controller;

import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer();
        DatamartTimerTask task = new DatamartTimerTask();
        timer.scheduleAtFixedRate(task, 10000, 3600000);
    }
}
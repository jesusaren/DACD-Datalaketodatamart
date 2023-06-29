package org.example.controller;


import java.util.TimerTask;

public class DatamartTimerTask extends TimerTask {
    public void run() {
        try {
            new Controller().datamartControl();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

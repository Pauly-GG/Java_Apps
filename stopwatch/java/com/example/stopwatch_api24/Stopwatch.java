package com.example.stopwatch_api24;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Stopwatch {
    private int hours, minutes, seconds;

    Stopwatch() {
        hours = minutes = seconds = 0;
    }

    public Stopwatch(String test) {

    }

    void tick() {
        seconds++;
        if (seconds == 60) {
            minutes++;
            seconds = 0;
        }
        if (minutes == 60) {
            hours++;
            minutes = 0;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(),
                "%02d:%02d:%02d", hours, minutes, seconds);
    }
}

package com.example.guessgame24;

import java.util.Random;

public class Game {

    private final int mySecret;
    public Game() {
        mySecret = new Random().nextInt(10) + 1; // 1-10
    }
    public boolean check(int i) {
        return (i == mySecret);
    }
}

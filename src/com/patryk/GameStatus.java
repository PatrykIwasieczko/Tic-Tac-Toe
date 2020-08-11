package com.patryk;

public enum GameStatus {

    X_TURN("Game not finished"),
    O_TURN("Game not finished"),
    X_WINS("X wins"),
    O_WINS("Y wins"),
    DRAW("Draw");

    private String name;

    GameStatus(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}

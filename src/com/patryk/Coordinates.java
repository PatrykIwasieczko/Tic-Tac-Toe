package com.patryk;

public class Coordinates {
    public int x;
    public int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isValid(){
        return x > 0 && x < 4 && y > 0 && y < 4;
    }
}

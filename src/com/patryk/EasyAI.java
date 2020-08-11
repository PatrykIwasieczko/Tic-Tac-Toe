package com.patryk;

import java.util.ArrayList;

public class EasyAI extends Player {

    private Field field;

    EasyAI(Game game) {
        super(game);
        field = game.getField();
    }

    @Override
    protected Coordinates getMove() {
        ArrayList<Coordinates> emptyCoordinates = field.getEmptyFields();
        Coordinates result = emptyCoordinates.get((int)(Math.random() * emptyCoordinates.size()));
        System.out.println("Making move level \"easy\"");
        return result;
    }
}

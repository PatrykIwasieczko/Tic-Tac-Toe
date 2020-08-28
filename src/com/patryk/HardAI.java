package com.patryk;

import java.util.ArrayList;

public class HardAI extends Player {

    Field field;
    Symbol enemyPlayer;

    public HardAI(Game game) {
        super(game);
        field = game.getField();
    }

    private int miniMax(Field checkedField, Symbol turn, boolean findMax) {

        Symbol winner = checkedField.getWinner();
        if (winner == humanPlayer) {
            return 1;
        } else if (winner == enemyPlayer) {
            return -1;
        } else if (!checkedField.haveEmptyFields()) {
            return 0;
        }

        // if this not the end of the game.
        int score = findMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;


        ArrayList<Coordinates> emptyCoordinates = checkedField.getEmptyFields();
        for (Coordinates currentMove : emptyCoordinates) {

            Field newField = new Field(checkedField.getField());

            newField.setCell(currentMove, turn);

            int currentScore = miniMax(newField, turn == Symbol.X ? Symbol.O : Symbol.X, !findMax);

            if (findMax && currentScore > score || !findMax && currentScore < score) {
                score = currentScore;
            }

        }
        return score;
    }

    @Override
    protected Coordinates getMove() {
        enemyPlayer = humanPlayer == Symbol.X ? Symbol.O : Symbol.X;
        System.out.println("Making move level \"hard\"");
        Coordinates move = null;

        int score = Integer.MIN_VALUE;
        Coordinates res = null;

        ArrayList<Coordinates> emptyCoordinates = field.getEmptyFields();
        for (Coordinates currentMove : emptyCoordinates) {

            Field newField = new Field(field.getField());

            newField.setCell(currentMove, humanPlayer);

            int currentScore = miniMax(newField, enemyPlayer, false);

            if (currentScore > score) {
                score = currentScore;
                res = currentMove;
            }

        }

        return res;
    }
}

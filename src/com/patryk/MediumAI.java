package com.patryk;

import java.util.ArrayList;

public class MediumAI extends Player {

    Field field;

    public MediumAI(Game game) {
        super(game);
        field = game.getField();
    }

    @Override
    protected Coordinates getMove() {
        Symbol enemyPlayer = humanPlayer == Symbol.X ? Symbol.O : Symbol.X;
        System.out.println("Making move level \"medium\"");
        Coordinates enemyWinnerMove = null;

        //Get possible moves
        ArrayList<Coordinates> emptyCoordinates = field.getEmptyFields();
        for (Coordinates currentMove : emptyCoordinates) {
            Field newField = new Field(field.getField());
            newField.setCell(currentMove, humanPlayer);

            if (newField.getWinner() == humanPlayer) {
                return currentMove;
            }

            newField.setCell(currentMove, enemyPlayer);

            if (newField.getWinner() == enemyPlayer) {
                enemyWinnerMove = currentMove;
            }
        }

        if (enemyWinnerMove != null) {
            return enemyWinnerMove;
        }

        return emptyCoordinates.get((int)(Math.random() * emptyCoordinates.size()));

    }
}

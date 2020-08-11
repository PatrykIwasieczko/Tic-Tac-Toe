package com.patryk;

public class Game {

    private Field field;
    private Symbol turn;
    private Player xPlayer = null;
    private Player oPlayer = null;

    public void setPlayers(Player xPlayer, Player oPlayer) {
        this.xPlayer= xPlayer;
        this.oPlayer = oPlayer;
    }

    Game() {
        field = new Field("_________");
        turn = Symbol.X;
    }

    public void setField(String stringFormat) {
        field = new Field(stringFormat);
    }

    public Field getField() {
        return field;
    }

    public GameStatus getStatus() {
        return field.getGameStatus();
    }

    public void start(){

        if (xPlayer == null || oPlayer == null) {
            System.out.print("Players are not initialized!");
            System.exit(1);
        }

        boolean playGame = true;
        Coordinates coordinates = null;
        GameStatus status = GameStatus.X_TURN;
        while (playGame) {

            showField();
            switch (turn){
                case X:
                    xPlayer.makeMove();
                    break;
                case O:
                    oPlayer.makeMove();
                    break;
            }

            status = getStatus();
            switch (status){
                case X_WINS:
                case O_WINS:
                case DRAW:
                    playGame = false;
                    break;
            }
        }

        showField();
        System.out.println(status);
    }

    private void showField() {
        Symbol[][] arr = field.toArray();
        System.out.print("---------\n");
        for (int i = 2; i > -1 ; i--) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j] == Symbol.EMPTY ? "  " : arr[i][j]+  " ");
            }
            System.out.print("|\n");
        }
        System.out.print("---------\n");
    }

    public boolean makeMove(Coordinates coordinates) {
        if (coordinates.isValid() && field.isCellEmpty(coordinates)) {
            field.setCell(coordinates, turn);
            nextTurn();
            return true;
        }
        return false;
    }

    private void nextTurn() {
        if (turn == Symbol.X) {
            turn = Symbol.O;
        } else {
            turn = Symbol.X;
        }
    }

    public Symbol getCurrentTurn() {
        return turn == Symbol.X ? Symbol.X : Symbol.O;
    }
}

package com.patryk;

abstract class Player {

    Game game;
    Symbol humanPlayer = null;

    Player(Game game) {
        this.game = game;
    }

    final public void makeMove(){
        Symbol currentTurn = null;
        switch (game.getStatus()) {
            case X_TURN:
                currentTurn = Symbol.X;
                break;
            case O_TURN:
                currentTurn = Symbol.O;
                break;
            default:
                System.out.print("Cannot make move");
                System.exit(1);
        }

        if (humanPlayer == null) {
            humanPlayer = currentTurn;
        } else if (humanPlayer != currentTurn) {
            System.out.print("Error making move. Players side was changing ");
            System.exit(1);
        }

        Coordinates coordinates = getMove();
        if (!game.makeMove(coordinates)) {
            System.out.print("Error making move");
            System.exit(1);
        }
    }

    abstract protected Coordinates getMove();
}

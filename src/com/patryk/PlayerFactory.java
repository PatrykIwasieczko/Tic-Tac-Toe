package com.patryk;

class PlayerFactory {
    static Player CreatePlayer(Game game, String difficulty) {
        Player player;
        switch (difficulty) {
            case "easy":
                return new EasyAI(game);
            case "user":
                return new HumanPlayer(game);
//            case "medium":
//                return new MediumAI(game);
//            case "hard":
//                return new HardAI(game);
        }
        return null;
    }
}

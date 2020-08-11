package com.patryk;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean playGame = true;
        while (playGame){
            String command = null;
            String xDifficulty = null;
            String oDifficulty = null;
            boolean invalidCommand = true;

            while (invalidCommand) {
                System.out.println("Type 3 words separated by a space");
                System.out.println("1 - type of action (start or exit)");
                System.out.println("2 - First player playing as X (user, easy, medium, hard)");
                System.out.println("3 - Second player playing as O (user, easy, medium, hard)");
                System.out.print("Input command: ");
                String input = scanner.nextLine();
                Scanner parseCommand = new Scanner(input);

                try {

                    if (parseCommand.hasNext("start|exit")) {
                        command = parseCommand.next();
                    } else {
                        throw new InputMismatchException("");
                    }

                    switch (command) {
                        case "exit":
                            invalidCommand = false;
                            playGame = false;
                            break;
                        case "start":

                            xDifficulty = parseCommand.next("easy|user|medium|hard");
                            oDifficulty = parseCommand.next("easy|user|medium|hard");

                            invalidCommand = false;
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Bad parameters!");
                }
            }
            if ("start".equals(command)) {
                Game game = new Game();
                Player xPlayer = PlayerFactory.CreatePlayer(game, xDifficulty);
                Player oPlayer = PlayerFactory.CreatePlayer(game, oDifficulty);

                game.setPlayers(xPlayer, oPlayer);

                game.start();
            }
        }
    }
}

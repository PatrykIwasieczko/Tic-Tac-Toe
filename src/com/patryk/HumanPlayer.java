package com.patryk;

import java.util.Scanner;

public class HumanPlayer extends Player {

    Field field;

    HumanPlayer(Game game) {
        super(game);
        field = game.getField();
    }

    @Override
    protected Coordinates getMove() {
        Scanner scanner = new Scanner(System.in);
        int x = 0;
        int y = 0;
        boolean validAnswer = false;
        do {
            System.out.print("Enter the coordinates: ");
            String coordinates = scanner.nextLine();
            try {
                String[] arr = coordinates.split("\\s+");
                x = Integer.parseInt(arr[0]);
                y = Integer.parseInt(arr[1]);

            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You should enter 2 numbers!");
                continue;
            }

            if ( x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.print("Coordinates should be from 1 to 3!\n");
                continue;
            }

            if (!field.isCellEmpty(new Coordinates(x, y))) {

                System.out.print("This cell is occupied! Choose another one!\n");
                continue;
            }

            validAnswer = true;

        } while (!validAnswer);

        return new Coordinates(x, y);
    }
}

package com.patryk;

import java.util.ArrayList;

public class Field {

    int field;

    Field(String stringFormat) {
        int counter = 0;
        int[][] f = new int[3][3];
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                f[i][j] = Symbol.getValue(stringFormat.charAt(counter));
                counter++;
            }
        }
        field = 0;

        for (int i = 2; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                field = (short) ((field * 3) + f[i][j]);
            }
        }
    }

    public Field(short field) {
        this.field = field;
    }

    public Symbol[][] toArray() {
        int f = this.field;
        Symbol[][] res = new Symbol[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res[i][j] = Symbol.getSymbol(f % 3);
                f /= 3;
            }
        }
        return res;
    }

    public int getFiled() {
        return field;
    }

    public Symbol getCell(Coordinates coordinates) {
        final int x = coordinates.x;
        final int y = coordinates.y;

        if (x > 3 || x < 1 || y > 3 || y < 1) {
            System.exit(1);
        }
        switch ((field / (int) Math.pow(3, (y - 1) * 3 + x - 1)) % 3) {
            case 1:
                return Symbol.X;
            case 2:
                return Symbol.O;
        }
        return Symbol.EMPTY;
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return getCell(coordinates) == Symbol.EMPTY;
    }

    private void setField(Symbol[][] arr) {
        field = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                field = (field * 3 + arr[i][j].getValue());
            }
        }

    }

    public void setCell(Coordinates coordinates, Symbol symbol) {
        final int x = coordinates.x;
        final int y = coordinates.y;
        if (x > 3 || x < 1 || y > 3 || y < 1) {
            System.exit(1); ;
        }
        Symbol[][] arr = toArray();
        arr[y - 1][x - 1] = symbol;
        setField(arr);
    }

    public Symbol getWinner() {
        Symbol[][] f = toArray();
        // check for wins

        for (int i = 0; i < 3; i++) {
            //columns
            if (f[0][i] == f[1][i] && f[1][i] == f[2][i] && f[0][i] != Symbol.EMPTY) {
                if (f[0][i] == Symbol.X) {
                    return Symbol.X;
                } else {
                    return Symbol.O;
                }
            }
            //rows
            if (f[i][0] == f[i][1] && f[i][1] == f[i][2] && f[i][0] != Symbol.EMPTY) {
                if (f[i][0] == Symbol.X) {
                    return Symbol.X;
                } else {
                    return Symbol.O;
                }
            }
        }

        //diagonals
        if (((f[1][1] == f[0][0] && f[1][1] == f[2][2])
                || (f[1][1] == f[0][2] && f[1][1] == f[2][0]))
                && f[1][1] != Symbol.EMPTY) {
            if (f[1][1] == Symbol.X) {
                return Symbol.X;
            } else {
                return Symbol.O;
            }
        }
        return Symbol.EMPTY;
    }

    public GameStatus getGameStatus() {

        Symbol winner = getWinner();

        switch (winner) {
            case X:
                return GameStatus.X_WINS;
            case O:
                return GameStatus.O_WINS;
        }

        Symbol[][] f = toArray();
        // check for finished and draw.
        int xCount = 0;
        int oCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (f[i][j]) {
                    case X:
                        xCount++;
                        break;
                    case O:
                        oCount++;
                        break;
                }
            }
        }

        if (xCount + oCount == 9) {
            return GameStatus.DRAW;
        }
        
        if (xCount == oCount) {
            return GameStatus.X_TURN;
        }

        return GameStatus.O_TURN;
    }

    public ArrayList<Coordinates> getEmptyFields() {
        ArrayList<Coordinates> res = new ArrayList<>();
        Symbol[][] array = toArray();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (array[i][j] == Symbol.EMPTY) {
                    res.add(new Coordinates(j + 1, i + 1));
                }
            }
        }
        return res;
    }

    public boolean haveEmptyFields() {
        int f = this.field;
        for (int i = 0; i < 9; i++) {
            if (f % 3 == Symbol.EMPTY.getValue()) {
                return true;
            }
            f /= 3;
        }
        return false;
    }
}

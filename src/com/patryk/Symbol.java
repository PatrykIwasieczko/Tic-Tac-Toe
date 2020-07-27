package com.patryk;

enum Symbol {
    EMPTY('_', 0),
    X('X', 1),
    O('O', 2);

    private char name;
    private int value;

    Symbol(char name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public char getName() {
        return name;
    }

    public static char getName(int value) {
        for (Symbol symbol : Symbol.values()) {
            if (symbol.getValue() == value) {
                return symbol.getName();
            }
        }
        return 0;
    }

    public static int getValue(char name) {
        for (Symbol symbol : Symbol.values()) {
            if (symbol.getName() == name) {
                return symbol.getValue();
            }
        }
        return 0;
    }

    public static Symbol getSymbol(int value){
        for (Symbol symbol : Symbol.values()) {
            if (symbol.getValue() == value) {
                return symbol;
            }
        }
        return EMPTY;
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }
}

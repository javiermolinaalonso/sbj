package com.socialblackjack.core;

/**
 * Created by javier on 18/07/15.
 */
public enum Color {

    HEARTH('h'),
    DIAMOND('d'),
    SPADE('s'),
    CLUB('c');

    private final char color;

    Color(char color) {
        this.color = color;
    }

    public char getColor() {
        return color;
    }
}

package com.socialblackjack.core;

/**
 * Created by javier on 18/07/15.
 */
public enum Rank {
    ACE('A'),
    KING('K'),
    QUEEN('Q'),
    JACK('J'),
    TEN('T'),
    NINE('9'),
    EIGHT('8'),
    SEVEN('7'),
    SIX('6'),
    FIVE('5'),
    FOUR('4'),
    THREE('3'),
    TWO('2');

    private final char rank;

    Rank(char rank) {
        this.rank = rank;
    }

    public char getRank() {
        return rank;
    }
}

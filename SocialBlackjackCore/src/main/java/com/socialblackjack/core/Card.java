package com.socialblackjack.core;

public interface Card {

    String ACE = "A";

    byte CARD_PER_COLOR = 13;
    Rank[] RANKS = {
            Rank.ACE, Rank.TWO, Rank.THREE,
            Rank.FOUR, Rank.FIVE, Rank.SIX,
            Rank.SEVEN, Rank.EIGHT, Rank.NINE,
            Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING
    };
    Color[] COLORS = {Color.HEARTH, Color.SPADE, Color.CLUB, Color.DIAMOND};

    /**
     * It is the current reference on the deck.
     * This value identifies the card, it must be between 0 and
     * 51, being 0 the
     *
     * @return
     */
    byte getCard();

    /**
     * Returns the value of the card, 0 is an Ace, 12 is a King
     *
     * @return
     */
    Integer getRankValue();

    /**
     * Returns the value of the card in standard format, A-ce, K-ing...
     */
    Rank getRank();

    /**
     * Returns the color of the card, h = Hearth, s = Spade, c = Club, d = Diamond
     *
     * @return
     */
    Color getColor();

    /**
     * Returns the value of the card in format RANK-COLOR.
     * For example, The ace of spades will return As
     *
     * @return
     */
    String getValue();

}

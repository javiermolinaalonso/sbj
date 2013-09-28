package com.socialblackjack.core;

public interface Card {

	public static final byte CARD_PER_COLOR = 13;
	public static final char[] RANKS = new char[]{'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
	public static final char[] COLORS = new char[]{'h', 's', 'c', 'd'};
	
	/**
	 * It is the current reference on the deck.
	 * This value identifies the card, it must be between 0 and
	 * 51, being 0 the 
	 * @return
	 */
	public byte getCard();
	
	/**
	 * Returns the value of the card, 0 is an Ace, 12 is a King
	 * @return
	 */
	public Integer getRankValue();
	
	/**
	 * Returns the value of the card in standard format, A-ce, K-ing...
	 */
	public String getRank();
	
	/**
	 * Returns the color of the card, h = Hearth, s = Spade, c = Club, d = Diamond
	 * @return
	 */
	public String getColor();
	
	/**
	 * Returns the value of the card in format RANK-COLOR.
	 * For example, The ace of spades will return As
	 * @return
	 */
	public String getValue();
	
}

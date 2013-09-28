package com.socialblackjack.core;

import java.util.Collection;

import com.socialblackjack.core.exceptions.DeckEmptyException;

public interface Deck {

	public static final Integer MIN_DECKS = 1;
	public static final Integer MAX_DECKS = 6;
	/**
	 * Shuffles the cards randomly.
	 * The used cards will be cleared
	 */
	public void shuffle();
	
	/**
	 * Return the cards that remains in the deck
	 */
	public Collection<Card> getAvailableCards();
	
	/**
	 * Return the used cards
	 */
	public Collection<Card> getUsedCards();
	
	/**
	 * Gets the card in the top of the deck
	 * Add this card to the used cards and removes it from the available cards
	 */
	public Card getFirst() throws DeckEmptyException;
	
}

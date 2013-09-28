package com.socialblackjack.core;

import java.util.Collection;
import java.util.List;

import com.socialblackjack.core.exceptions.MaximumCardsReached;

public interface Hand {

	/**
	 * Adds the card to the current hand
	 * @param card
	 * @throws MaximumCardsReached if the hand has arrived to the maximum size
	 */
	public void addCard(Card card) throws MaximumCardsReached;
	
	/**
	 * Returns the higher value of the current hand.
	 * In poker, 54321 could be an straight or a color, this method will return the color value
	 * In blackjack, for example, A5 could be 6 or 16, this method will return 16
	 * @return
	 */
	public <T extends Number> Number getHigherValue();
	
	/**
	 * Returns all the possible values that the hand could have
	 * In blackjack, A5 will return <6,16>
	 * @return
	 */
	public Collection<? extends Number> getValues();
	
	/**
	 * Returns the cards that anyone can see
	 * @return
	 */
	public Collection<Card> getShowableCards();
	
	/**
	 * Returns the cards that only the owner can see
	 * @return
	 */
	public Collection<Card> getHideCards();
	
	/**
	 * Returns all the cards of the hand
	 * @return
	 */
	public List<Card> getCards();
	
}

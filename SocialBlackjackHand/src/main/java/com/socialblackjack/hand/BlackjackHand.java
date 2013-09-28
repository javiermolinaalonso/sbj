package com.socialblackjack.hand;

import java.util.Collection;

import com.socialblackjack.core.Hand;

public interface BlackjackHand extends Hand {

	public static final Integer MAX_VALUE = 21;
	public static final Integer MAX_CARDS = 7;
	
	public <T extends Number> Integer getHigherValue();
	
	/**
	 * Returns a collection with all the possible values of the current hand
	 */
	public Collection<Integer> getValues();
	
	/**
	 * Returns true if the hand is an ACE and T, J, Q or K
	 * @return
	 */
	public boolean isBlackjack();

	/**
	 * Returns true if the hand is a pair and is in possession of a player, else returns false
	 * @return
	 */
	public boolean isSplittable();
	
	/**
	 * Returns true if the value exceeds MAX_VALUE else returns false
	 * @return
	 */
	public boolean isBust();
	
	/**
	 * Returns true if the hand contains an Ace
	 * @return
	 */
	public boolean isSoft();
}

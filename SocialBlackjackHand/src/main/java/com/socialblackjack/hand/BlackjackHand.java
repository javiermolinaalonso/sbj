package com.socialblackjack.hand;

import com.socialblackjack.core.Hand;

import java.util.Collection;

public interface BlackjackHand extends Hand<Integer> {

	Integer MAX_VALUE = 21;
	Integer MAX_CARDS = 7;
	
	Integer getHigherValue();
	
	/**
	 * Returns a collection with all the possible values of the current hand
	 */
	Collection<Integer> getValues();
	
	/**
	 * Returns true if the hand is an ACE and T, J, Q or K
	 * @return
	 */
	boolean isBlackjack();

	/**
	 * Returns true if the hand is a pair and is in possession of a player, else returns false
	 * @return
	 */
	boolean isSplittable();
	
	/**
	 * Returns true if the value exceeds MAX_VALUE else returns false
	 * @return
	 */
	boolean isBust();
	
	/**
	 * Returns true if the hand contains an Ace
	 * @return
	 */
	boolean isSoft();
}

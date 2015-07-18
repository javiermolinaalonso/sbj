package com.socialblackjack.hand.impl;

import com.socialblackjack.core.Card;
import com.socialblackjack.core.exceptions.MaximumCardsReached;
import com.socialblackjack.hand.BlackjackHand;
import com.socialblackjack.hand.utils.HandUtils;

import java.util.*;

public abstract class BlackjackHandImpl implements BlackjackHand {

	protected List<Card> cards;
	
	public abstract Collection<Card> getShowableCards();

	public abstract Collection<Card> getHideCards();

	public abstract boolean isSplittable();
	
	public void addCard(Card card) throws MaximumCardsReached {
		if(cards == null)
			cards = new ArrayList<Card>();
		if(cards.size() == MAX_CARDS)
			throw new MaximumCardsReached();
		cards.add(card);
	}

	public Integer getHigherValue() {
		Integer max = 0;
		for(Integer v : getValues()){
			max = Math.max(max, v);
		}
		return max;
	}

	public Collection<Integer> getValues() {
		if(cards == null)
			return new ArrayList<Integer>();

		LinkedList<Integer> currentValidResults = new LinkedList<Integer>();
		currentValidResults.add(0);
		for(Card card : cards){
			List<Integer> auxList = (List<Integer>) currentValidResults.clone();
			currentValidResults.clear();
			for(Integer curValue : HandUtils.getValuesFromRank(card.getRank())){
				for(Integer acumValue : auxList){
					currentValidResults.add(acumValue+curValue);
				}
			}
		}
		Iterator<Integer> it = currentValidResults.iterator();
		while(it.hasNext()){
			if(currentValidResults.size() == 1)
				break;
			if(it.next() > MAX_VALUE){
				it.remove();
			}
		}
		return currentValidResults;
	}

	public boolean isBlackjack() {
		return MAX_VALUE.equals(getHigherValue()) && cards.size() == 2;
	}

	public boolean isBust() {
		return getHigherValue() > MAX_VALUE;
	}

	public boolean isSoft() {
		return cards.get(0).getRank().equals(Card.ACE) || cards.get(1).getRank().equals(Card.ACE);
	}
}

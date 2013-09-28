package com.socialblackjack.hand.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.socialblackjack.core.Card;

public class BlackjackHandDealer extends BlackjackHandImpl {

	private boolean allowShowAllCards;
	
	public BlackjackHandDealer(){
		super();
		setAllowShowAllCards(false);
	}
	
	public List<Card> getCards() {
		return getShowableCards();
	}

	@Override
	public List<Card> getShowableCards() {
		if(allowShowAllCards)
			return cards;
		else
			return cards.subList(0, 1);
	}

	@Override
	public Collection<Card> getHideCards() {
		if(allowShowAllCards)
			return new ArrayList<Card>();
		else
			return cards.subList(1, 2);
	}

	@Override
	public boolean isSplittable() {
		return false;
	}

	public void setAllowShowAllCards(boolean allowShowAllCards) {
		this.allowShowAllCards = allowShowAllCards;
	}

}

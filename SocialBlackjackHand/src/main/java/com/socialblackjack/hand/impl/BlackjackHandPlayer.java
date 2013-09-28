package com.socialblackjack.hand.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.socialblackjack.core.Card;

public class BlackjackHandPlayer extends BlackjackHandImpl {

	public Collection<Card> getShowableCards(){
		return cards;
	}
	
	public List<Card> getCards() {
		return cards;
	}

	@Override
	public Collection<Card> getHideCards() {
		return new ArrayList<Card>();
	}

	public boolean isSplittable() {
		return cards.size() == 2 && cards.get(0).getRankValue().equals(cards.get(1).getRankValue());
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(Card c : cards){
			sb.append(c.getValue());
		}
		return sb.toString();
	}

}

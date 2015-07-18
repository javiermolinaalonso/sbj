package com.socialblackjack.hand.impl;

import com.socialblackjack.core.Card;
import com.socialblackjack.core.Rank;
import com.socialblackjack.hand.utils.HandUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        return haveTwoCards() && bothCardsHaveSameValue();
	}

    private boolean bothCardsHaveSameValue() {
        return isBothAces() || isBothCourt() || isBothSameValue();
    }

    private boolean isBothSameValue() {
        return cards.get(0).getRank().equals(cards.get(1).getRank());
    }

    private boolean isBothCourt() {
        return HandUtils.getValues(cards.get(0).getRank()).get(0).equals(10)
                && HandUtils.getValues(cards.get(1).getRank()).get(0).equals(10);
    }

    private boolean isBothAces() {
        return Rank.ACE.equals(cards.get(0).getRank())
                && Rank.ACE.equals(cards.get(1).getRank());
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

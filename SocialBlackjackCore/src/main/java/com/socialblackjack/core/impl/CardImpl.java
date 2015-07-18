package com.socialblackjack.core.impl;

import com.socialblackjack.core.Card;
import com.socialblackjack.core.exceptions.InvalidColorException;
import com.socialblackjack.core.exceptions.InvalidRankException;

public class CardImpl implements Card {

	public static final int CARDS_PER_COLOR = 13;
	public static final int AMOUNT_COLORS = 4;

	private final byte card;
	
	public CardImpl(char rank, char color){
		card = getCardFromRankAndColor(rank, color);
	}

    public CardImpl(byte card){
        this.card = card;
    }

	private byte getCardFromRankAndColor(char rank, char color) {
		boolean found = false;
		byte card = 0;
		for(int i = 0; i < COLORS.length; i++){
			if(COLORS[i] == color){
				card = (byte) (CARD_PER_COLOR * i);
				found = true;
			}
		}
		if(!found){
			throw new InvalidRankException();
		}
		found = false;
		for(int i = 0; i < RANKS.length; i++){
			if(RANKS[i] == rank){
				card += i ;
				found = true;
			}
		}
		if(!found){
			throw new InvalidColorException();
		}
        return card;
	}

	public byte getCard() {
		return card;
	}

	public Integer getRankValue() {
		return card % CARDS_PER_COLOR;
	}

	public String getRank() {
		return String.valueOf(RANKS[card % CARDS_PER_COLOR]);
	}

	public String getColor() {
		return String.valueOf(COLORS[card / CARDS_PER_COLOR % AMOUNT_COLORS]);
	}

	public String getValue() {
		StringBuilder sb = new StringBuilder();
		sb.append(RANKS[card % 13]).append(COLORS[card / CARDS_PER_COLOR % AMOUNT_COLORS]);
		return sb.toString();
	}

}

package com.socialblackjack.core.impl;

import com.socialblackjack.core.Card;
import com.socialblackjack.core.exceptions.InvalidColorException;
import com.socialblackjack.core.exceptions.InvalidRankException;

public class CardImpl implements Card {

	private byte card;
	
	public CardImpl(char rank, char color){
		card = 0;
		boolean found = false;
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
	}
	
	public CardImpl(byte card){
		this.card = card;
	}
	
	public byte getCard() {
		return card;
	}

	public Integer getRankValue() {
		return card % 13;
	}

	public String getRank() {
		return String.valueOf(RANKS[card % 13]);
	}

	public String getColor() {
		return String.valueOf(COLORS[card / 13 % 4]);
	}

	public String getValue() {
		StringBuilder sb = new StringBuilder();
		sb.append(RANKS[card % 13]).append(COLORS[card / 13 % 4]);
		return sb.toString();
	}

}

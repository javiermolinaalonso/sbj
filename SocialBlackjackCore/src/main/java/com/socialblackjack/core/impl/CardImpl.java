package com.socialblackjack.core.impl;

import com.socialblackjack.core.Card;
import com.socialblackjack.core.Color;
import com.socialblackjack.core.Rank;
import com.socialblackjack.core.exceptions.InvalidColorException;
import com.socialblackjack.core.exceptions.InvalidRankException;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CardImpl implements Card {

	public static final int CARDS_PER_COLOR = 13;
	public static final int AMOUNT_COLORS = 4;

	private final byte card;
    private final Rank rank;
    private final Color color;

    public static Card of(Rank rank, Color color) {
        return new CardImpl(rank, color);
    }

	private CardImpl(Rank rank, Color color){
		card = getCardFromRankAndColor(rank, color);
        this.rank = rank;
        this.color = color;
	}

    public CardImpl(byte card){
        this.card = card;
        this.rank = RANKS[card % CARDS_PER_COLOR];
        this.color = COLORS[card / CARDS_PER_COLOR % AMOUNT_COLORS];
    }

	private byte getCardFromRankAndColor(Rank rank, Color color) {
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

	public Rank getRank() {
		return rank;
	}

	public Color getColor() {
		return color;
	}

    public String getValue() {
        StringBuffer sb = new StringBuffer(2);
        return sb.append(rank.getRank()).append(color.getColor()).toString();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("rank", rank)
                .append("color", color)
                .toString();
    }

}

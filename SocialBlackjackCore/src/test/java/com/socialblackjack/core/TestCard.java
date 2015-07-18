package com.socialblackjack.core;

import com.socialblackjack.core.impl.CardImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCard {

	@Test
	public void testCardImpl() {
		Card card = CardImpl.of(Rank.ACE, Color.SPADE);
		assertEquals('A', card.getRank().getRank());
		assertEquals('s', card.getColor().getColor());
		assertEquals((byte) 13, card.getCard());
		assertEquals((Integer)0, card.getRankValue());
		assertEquals("As", card.getValue());
		card = CardImpl.of(Rank.KING, Color.DIAMOND);
		assertEquals('K', card.getRank().getRank());
		assertEquals('d', card.getColor().getColor());
		assertEquals((byte) 51, card.getCard());
		assertEquals((Integer)12, card.getRankValue());
		assertEquals("Kd", card.getValue());
		card = new CardImpl((byte) 50);
		assertEquals('Q', card.getRank().getRank());
		assertEquals('d', card.getColor().getColor());
		assertEquals((byte) 50, card.getCard());
		assertEquals((Integer)11, card.getRankValue());
		assertEquals("Qd", card.getValue());
	}

}

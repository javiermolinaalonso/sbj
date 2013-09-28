package com.socialblackjack.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.socialblackjack.core.impl.CardImpl;

public class TestCard {

	@Test
	public void testCardImpl() {
		Card card = new CardImpl('A', 's');
		assertEquals("A", card.getRank());
		assertEquals("s", card.getColor());
		assertEquals((byte) 13, card.getCard());
		assertEquals((Integer)0, card.getRankValue());
		assertEquals("As", card.getValue());
		card = new CardImpl('K', 'd');
		assertEquals("K", card.getRank());
		assertEquals("d", card.getColor());
		assertEquals((byte) 51, card.getCard());
		assertEquals((Integer)12, card.getRankValue());
		assertEquals("Kd", card.getValue());
		card = new CardImpl((byte) 50);
		assertEquals("Q", card.getRank());
		assertEquals("d", card.getColor());
		assertEquals((byte) 50, card.getCard());
		assertEquals((Integer)11, card.getRankValue());
		assertEquals("Qd", card.getValue());
	}

}

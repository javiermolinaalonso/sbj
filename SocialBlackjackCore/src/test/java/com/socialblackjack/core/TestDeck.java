package com.socialblackjack.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.socialblackjack.core.exceptions.InvalidAmountDecks;
import com.socialblackjack.core.impl.DeckImpl;

public class TestDeck {

	@Test
	public void test() {
		Deck d = new DeckImpl();
		assertEquals(52, d.getAvailableCards().size());
		d = new DeckImpl(2);
		assertEquals(104, d.getAvailableCards().size());
	}
	
	@Test(expected=InvalidAmountDecks.class)
	public void testMinDecks(){
		new DeckImpl(Deck.MIN_DECKS-1);
	}
	
	@Test(expected=InvalidAmountDecks.class)
	public void testMaxDecks(){
		new DeckImpl(Deck.MAX_DECKS+1);
	}

}

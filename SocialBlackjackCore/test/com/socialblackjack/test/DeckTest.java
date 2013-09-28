package com.socialblackjack.test;

import org.junit.Test;

import com.socialblackjack.core.Deck;
import com.socialblackjack.core.impl.DeckImpl;

public class DeckTest {

	@Test
	public void shuffle() {
		Deck d = new DeckImpl(6);
		System.out.println(d.toString());
		d.shuffle();
		System.out.println(d.toString());
		d.shuffle();
		System.out.println(d.toString());
		d.shuffle();
		System.out.println(d.toString());
	}
	
}

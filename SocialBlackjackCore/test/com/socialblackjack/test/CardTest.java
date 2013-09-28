package com.socialblackjack.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.socialblackjack.core.Card;
import com.socialblackjack.core.impl.CardImpl;

public class CardTest {

	@Test
	public void testCardImpl() {
		for(byte i = 0; i < 52; i++){
			Card c = new CardImpl(i);
		}
	}
	
	@Test
	public void testGetValue(){
		Card aceSpades = new CardImpl((byte)13);
		assertEquals("As", aceSpades.getValue());
	}

	@Test
	public void testGetCard() {
		Card ten = new CardImpl((byte)9);
		assertEquals((byte)9, ten.getCard());
	}

	@Test
	public void testGetRankValue() {
		Card ten = new CardImpl((byte)9);
		assertEquals(Integer.valueOf(9), ten.getRankValue());
		Card jack = new CardImpl((byte)23);
		assertEquals(Integer.valueOf(10), jack.getRankValue());
		Card king = new CardImpl((byte)51);
		assertEquals(Integer.valueOf(12), king.getRankValue());
	}

	@Test
	public void testGetColor() {
		Card hearth = new CardImpl((byte)9);
		assertEquals("h", hearth.getColor());
		Card spade = new CardImpl((byte)22);
		assertEquals("s", spade.getColor());
		Card club = new CardImpl((byte)35);
		assertEquals("c", club.getColor());
		Card diamond = new CardImpl((byte)50);
		assertEquals("d", diamond.getColor());
	}

	@Test
	public void testGetRank() {
		Card ten = new CardImpl((byte)9);
		assertEquals("T", ten.getRank());
		Card jack = new CardImpl((byte)23);
		assertEquals("J", jack.getRank());
		Card king = new CardImpl((byte)51);
		assertEquals("K", king.getRank());
	}
}

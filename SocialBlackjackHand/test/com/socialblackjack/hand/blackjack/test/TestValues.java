package com.socialblackjack.hand.blackjack.test;

import org.junit.Test;

import com.socialblackjack.core.Deck;
import com.socialblackjack.core.impl.DeckImpl;
import com.socialblackjack.hand.blackjack.BlackjackHand;
import com.socialblackjack.hand.blackjack.impl.BlackjackHandImpl;
import com.socialblackjack.hand.blackjack.impl.BlackjackHandPlayer;

public class TestValues {

	@Test
	public void test() {
		Deck deck = new DeckImpl(6);
		deck.shuffle();
		for(int i = 0; i < 50; i++){
			BlackjackHand hand = new BlackjackHandPlayer();
			for(int j = 0; j < 3; j++){
				hand.addCard(deck.getFirst());
			}
//			System.out.println(hand.toString()+": "+hand.getValues() + " - "+hand.getHigherValue());
		}
	}
	
	@Test
	public void testSoft() {
		for(int i = 0; i < 10; i++){
			Deck deck = new DeckImpl(6);
			deck.shuffle();
			BlackjackHand hand = new BlackjackHandPlayer();
			for(int j = 0; j < 2; j++){
				hand.addCard(deck.getFirst());
			}
//			System.out.println(hand.toString() + ": "+hand.isSoft());
		}
	}
	
	@Test
	public void testBlackjack() {
		for(int i = 0; i < 10; i++){
			Deck deck = new DeckImpl(6);
			deck.shuffle();
			BlackjackHand hand = new BlackjackHandPlayer();
			for(int j = 0; j < 2; j++){
				hand.addCard(deck.getFirst());
			}
			System.out.println(hand.toString() + ": "+hand.isBlackjack());
		}
	}

}

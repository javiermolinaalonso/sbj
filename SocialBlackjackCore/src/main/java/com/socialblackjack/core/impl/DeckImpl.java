package com.socialblackjack.core.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

import com.socialblackjack.core.Card;
import com.socialblackjack.core.Deck;
import com.socialblackjack.core.exceptions.DeckEmptyException;
import com.socialblackjack.core.exceptions.InvalidAmountDecks;

public class DeckImpl implements Deck {

	private Stack<Card> cards;
	private Vector<Card> usedCards;
	
	public DeckImpl(){
		this(MIN_DECKS);
	}
	
	public DeckImpl(int decks){
		if(decks < MIN_DECKS || decks > MAX_DECKS){
			throw new InvalidAmountDecks();
		}
		cards = new Stack<Card>();
		for (int i=0; i < 52 * decks; i++) {
			cards.add(new CardImpl((byte) (i % 52)));
		}
		usedCards = new Vector<Card>(0);
	}
	
	public void shuffle() {
		Random rgen = new Random();
		int max = cards.size();
		cards.addAll(usedCards);
		usedCards.clear();
		Long seed = Math.abs(Long.valueOf(Math.abs(System.nanoTime()) % 203894));
		for(int i = 0; i < 10000; i++){
			int origin = rgen.nextInt(seed.intValue()+1) % max;
			int end = rgen.nextInt(seed.intValue()+1) % max;
			Collections.swap(cards, origin, end);
		}
	}

	public Collection<Card> getAvailableCards() {
		return cards;
	}

	public Collection<Card> getUsedCards() {
		return usedCards;
	}

	public Card getFirst() throws DeckEmptyException {
		if(cards.isEmpty())
			throw new DeckEmptyException();
		return cards.pop();
	}

	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("Available: ");
		for(Card c : cards){
			sb.append(c.getValue()+ " ");
		}
		sb.append(". Used: ");
		for(Card c : usedCards){
			sb.append(c.getValue() + " ");
		}
		return sb.toString();
	}
}

package com.socialblackjack.hand.impl;

import com.socialblackjack.core.Card;
import com.socialblackjack.core.Rank;
import com.socialblackjack.core.exceptions.MaximumCardsReached;
import com.socialblackjack.hand.BlackjackHand;
import com.socialblackjack.hand.utils.HandUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This is the basic implementation of a blackjack hand
 *
 * The constructor
 */
public abstract class BlackjackHandImpl implements BlackjackHand {

	protected List<Card> cards;
	
	public BlackjackHandImpl() {
		this.cards = new ArrayList<>();
	}

	public void addCard(Card card) throws MaximumCardsReached {
		if(cards.size() == MAX_CARDS)
			throw new MaximumCardsReached();
		cards.add(card);
	}

	public Integer getHigherValue() {
		Integer max = 0;
		for(Integer v : getValues()){
			max = Math.max(max, v);
		}
		return max;
	}

	public List<Integer> getValues() {
        if(containsAnAce()) {
            Set<Integer> result = new HashSet<>();
            List<List<Integer>> acesList = cards.stream().filter(x -> Rank.ACE.equals(x.getRank())).map(x -> HandUtils.getValues(x.getRank())).collect(Collectors.toList());
            Integer resultWithoutAces = cards.stream().filter(x -> !Rank.ACE.equals(x.getRank())).map(x -> HandUtils.getValue(x.getRank())).reduce((x, y) -> x + y).get();
            for (List<Integer> integers : acesList) {
                for (Integer integer : integers) {
                    result.add(integer + resultWithoutAces);
                }
            }
            return result.stream().filter(x -> x <= MAX_VALUE).sorted().collect(Collectors.toList());
        } else {
            List<Integer> result = new ArrayList<>();
            Integer value = cards.stream().map(x -> HandUtils.getValue(x.getRank())).reduce((x, y) -> x + y).get();
            result.add(value);
            return result;
        }
//		LinkedList<Integer> currentValidResults = new LinkedList<>();
//		currentValidResults.add(0);
//		for(Card card : cards){
//			List<Integer> auxList = new ArrayList<>(currentValidResults);
//			currentValidResults.clear();
//			for(Integer curValue : HandUtils.getValues(card.getRank())){
//				for(Integer acumValue : auxList){
//					currentValidResults.add(acumValue+curValue);
//				}
//			}
//		}
//		Iterator<Integer> it = currentValidResults.iterator();
//		while(it.hasNext()){
//			if(currentValidResults.size() == 1)
//				break;
//			if(it.next() > MAX_VALUE){
//				it.remove();
//			}
//		}
//        currentValidResults.sort(Integer::compareTo);
//		return currentValidResults;
	}

    private boolean containsAnAce() {
        return cards.stream().anyMatch(x -> Rank.ACE.equals(x.getRank()));
    }

    public boolean isBlackjack() {
		return haveTwoCards() && MAX_VALUE.equals(getHigherValue());
	}

	public boolean isBust() {
		return getHigherValue() > MAX_VALUE;
	}

	public boolean isSoft() {
        return Rank.ACE.equals(cards.get(0).getRank())
                || Rank.ACE.equals(cards.get(1).getRank());
	}

    protected boolean haveTwoCards() {
        return cards.size() == 2 ;
    }

    public abstract Collection<Card> getShowableCards();

    public abstract Collection<Card> getHideCards();

    public abstract boolean isSplittable();
}

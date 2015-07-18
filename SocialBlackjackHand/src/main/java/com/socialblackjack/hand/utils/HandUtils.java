package com.socialblackjack.hand.utils;

import com.socialblackjack.core.Rank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.socialblackjack.core.Rank.*;

public class HandUtils {

	private static final Map<Rank, List<Integer>> mapCardValue = new HashMap<Rank, List<Integer>>();
    static {
        mapCardValue.put(ACE, Arrays.asList(1, 11));
        mapCardValue.put(TWO, Arrays.asList(2));
        mapCardValue.put(THREE, Arrays.asList(3));
        mapCardValue.put(FOUR, Arrays.asList(4));
        mapCardValue.put(FIVE, Arrays.asList(5));
        mapCardValue.put(SIX, Arrays.asList(6));
        mapCardValue.put(SEVEN, Arrays.asList(7));
        mapCardValue.put(EIGHT, Arrays.asList(8));
        mapCardValue.put(NINE, Arrays.asList(9));
        mapCardValue.put(TEN, Arrays.asList(10));
        mapCardValue.put(JACK, Arrays.asList(10));
        mapCardValue.put(QUEEN, Arrays.asList(10));
        mapCardValue.put(KING, Arrays.asList(10));
    }
    
    public static List<Integer> getValues(Rank rank){
    	return mapCardValue.get(rank);
    }

    /**
     * This method is a simplification for not returning always a list. If rank is an ace an exception is thrown
     * @param rank
     * @return
     */
    public static Integer getValue(Rank rank) {
        if(ACE.equals(rank)) {
            throw new IllegalArgumentException("Rank ACE is not allowed here");
        }
        return getValues(rank).get(0);
    }
    
}
package com.socialblackjack.hand.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandUtils {

	private static final Map<Integer, List<Integer>> mapCardValue = new HashMap<Integer, List<Integer>>();
    static {
        mapCardValue.put(0, Arrays.asList(1, 11));
        mapCardValue.put(1, Arrays.asList(2));
        mapCardValue.put(2, Arrays.asList(3));
        mapCardValue.put(3, Arrays.asList(4));
        mapCardValue.put(4, Arrays.asList(5));
        mapCardValue.put(5, Arrays.asList(6));
        mapCardValue.put(6, Arrays.asList(7));
        mapCardValue.put(7, Arrays.asList(8));
        mapCardValue.put(8, Arrays.asList(9));
        mapCardValue.put(9, Arrays.asList(10));
        mapCardValue.put(10, Arrays.asList(10));
        mapCardValue.put(11, Arrays.asList(10));
        mapCardValue.put(12, Arrays.asList(10));
    }
    
    private static final Map<String, List<Integer>> mapCardRank = new HashMap<String, List<Integer>>();
    static {
    	mapCardRank.put("A", Arrays.asList(1, 11));
    	mapCardRank.put("2", Arrays.asList(2));
    	mapCardRank.put("3", Arrays.asList(3));
        mapCardRank.put("4", Arrays.asList(4));
        mapCardRank.put("5", Arrays.asList(5));
        mapCardRank.put("6", Arrays.asList(6));
        mapCardRank.put("7", Arrays.asList(7));
        mapCardRank.put("8", Arrays.asList(8));
        mapCardRank.put("9", Arrays.asList(9));
        mapCardRank.put("T", Arrays.asList(10));
        mapCardRank.put("J", Arrays.asList(10));
        mapCardRank.put("Q", Arrays.asList(10));
        mapCardRank.put("K", Arrays.asList(10));
    }
	
    public static List<Integer> getValuesFromRank(String rank){
    	return mapCardRank.get(rank);
    }
    
    public static List<Integer> getValuesFromValue(Integer value){
    	return mapCardValue.get(value);
    }
    
}
package com.socialblackjack.betting.exceptions;

public class MaximumPlayerCoinsExceededException extends BettingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6517060052468493802L;

	public MaximumPlayerCoinsExceededException(Double betValue, Double maxValue){
		super("You have "+maxValue+" coins but you put "+betValue);
	}
}

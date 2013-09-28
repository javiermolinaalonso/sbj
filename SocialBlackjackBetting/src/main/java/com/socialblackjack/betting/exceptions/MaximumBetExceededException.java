package com.socialblackjack.betting.exceptions;

public class MaximumBetExceededException extends BettingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6517060052468493802L;

	public MaximumBetExceededException(Double betValue, Double maxValue){
		super("The maximum bet is "+maxValue+" but you put "+betValue);
	}
}

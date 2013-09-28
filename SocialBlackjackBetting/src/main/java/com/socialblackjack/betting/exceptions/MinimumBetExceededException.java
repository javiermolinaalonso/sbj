package com.socialblackjack.betting.exceptions;

public class MinimumBetExceededException extends BettingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6517060052468493802L;

	public MinimumBetExceededException(Double betValue, Double minValue){
		super("The minimum bet is "+minValue+" but you put "+betValue);
	}
}

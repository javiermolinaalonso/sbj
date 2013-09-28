package com.socialblackjack.betting.exceptions;

public abstract class BettingException extends RuntimeException {

	private static final long serialVersionUID = 900866214132643615L;

	public BettingException(String message){
		super(message);
	}
}

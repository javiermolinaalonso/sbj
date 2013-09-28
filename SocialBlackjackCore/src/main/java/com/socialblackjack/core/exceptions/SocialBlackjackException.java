package com.socialblackjack.core.exceptions;

public class SocialBlackjackException extends RuntimeException {

	private ExceptionEnumeration exc;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1381129424815201920L;

	public SocialBlackjackException(ExceptionEnumeration exc){
		this.exc = exc;
	}
	
	public ExceptionEnumeration getExceptionEnumeration(){
		return exc;
	}
}

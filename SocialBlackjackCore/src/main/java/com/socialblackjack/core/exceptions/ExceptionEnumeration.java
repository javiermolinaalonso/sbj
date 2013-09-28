package com.socialblackjack.core.exceptions;

public enum ExceptionEnumeration {

	TABLE_NOT_FOUND("TAB_NF");

	private String exceptionCode;
	
	private ExceptionEnumeration(String s){
		this.exceptionCode = s;
	}
	
	public String getExceptionCode(){
		return exceptionCode;
	}
}

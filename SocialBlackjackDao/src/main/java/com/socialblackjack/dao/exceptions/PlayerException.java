package com.socialblackjack.dao.exceptions;


import com.socialblackjack.dao.exceptions.code.PlayerExceptionCodeEnum;

public class PlayerException extends RuntimeException {

	private static final long serialVersionUID = -3203107619289727053L;
	
	private PlayerExceptionCodeEnum code;
	
	public PlayerException(PlayerExceptionCodeEnum code){
		super();
		this.code = code;
	}

	public PlayerExceptionCodeEnum getCode() {
		return code;
	}

}

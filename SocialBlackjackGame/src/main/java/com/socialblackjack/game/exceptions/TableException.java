package com.socialblackjack.game.exceptions;

import com.socialblackjack.dao.exceptions.code.TableExceptionCodeEnum;

public class TableException extends RuntimeException {

	private static final long serialVersionUID = -3203107619289727053L;
	
	private TableExceptionCodeEnum code;
	
	public TableException(TableExceptionCodeEnum code){
		super();
		this.code = code;
	}

	public TableExceptionCodeEnum getCode() {
		return code;
	}

}

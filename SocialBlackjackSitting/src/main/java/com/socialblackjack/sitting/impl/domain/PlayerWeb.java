package com.socialblackjack.sitting.impl.domain;

import com.socialblackjack.game.entities.Player;

public class PlayerWeb extends Player {

	private String signature;
	private String uniqueToken;

	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getUniqueToken() {
		return uniqueToken;
	}
	public void setUniqueToken(String uniqueToken) {
		this.uniqueToken = uniqueToken;
	}
	
}

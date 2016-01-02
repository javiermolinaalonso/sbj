package com.socialblackjack.game;

public interface State {

	/**
	 * Starts a new round of the current step
	 */
	void start(String table);
}

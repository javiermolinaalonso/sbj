package com.socialblackjack.dao;

import com.socialblackjack.entities.Player;

public interface PlayerDao {

	/**
	 * Loads a player identified by it's token
	 * @param token
	 * @return
	 */
	public Player loadPlayer(String token);
}

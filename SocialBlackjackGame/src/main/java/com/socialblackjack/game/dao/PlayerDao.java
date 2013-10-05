package com.socialblackjack.game.dao;

import com.socialblackjack.game.entities.Player;

public interface PlayerDao {

	/**
	 * Loads a player identified by it's token
	 * @param token
	 * @return
	 */
	public Player loadPlayer(String token);
}

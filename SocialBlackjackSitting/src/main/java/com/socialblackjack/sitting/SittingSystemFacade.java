package com.socialblackjack.sitting;

import com.socialblackjack.game.entities.Player;
import com.socialblackjack.game.entities.GameTable;

public interface SittingSystemFacade {

	/**
	 * Try to sit the player into the selected table. 
	 * @param player
	 * @param table
	 */
	public void attemptSit(Player player, GameTable table);
	
	/**
	 * Cancels an attempt of seating at the table. 
	 * It is only possible if the player is in "waiting" status
	 * @param player
	 * @param table
	 */
	public void cancelAttempt(Player player, GameTable table);
}

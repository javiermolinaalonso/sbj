package com.socialblackjack.betting;

import com.socialblackjack.betting.exceptions.MaximumBetExceededException;
import com.socialblackjack.betting.exceptions.MaximumPlayerCoinsExceededException;
import com.socialblackjack.betting.exceptions.MinimumBetExceededException;
import com.socialblackjack.entities.Player;

public interface BettingSystemController {

	/**
	 * Places a bet for the current player
	 * @param player The player who do the bet
	 * @param bet The amount
	 * @throws MaximumBetExceededException If the player bet more coins than allowed for the table
	 * @throws MinimumBetExceededException If the player bet less coins than allowed for the table
	 * @throws MaximumPlayerCoinsExceededException If the player attempts to put more money than he have
	 */
	void placeBet(Player player, Double bet) throws MaximumBetExceededException, MinimumBetExceededException, MaximumPlayerCoinsExceededException;
	
	/**
	 * Cancels a bet if the player previously did 
	 * @param player
	 */
	void cancelBet(Player player);
}

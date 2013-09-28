package com.socialblackjack.sitting;

import com.socialblackjack.game.entities.Player;

/**
 * This class is the controller for one table instance which is 
 * autowired.
 * @author javi
 *
 */
public interface SittingSystemController {

	/**
	 * Attemps to seat the player to the current table
	 * If there are seats available then it will sit and change 
	 * the status to SITTING, else it will change the status to
	 * WAITING
	 * @param player
	 */
	public String attemptSeat(Player player, String tableIdentifier, Integer seat);
	
	/**
	 * Sits out the player on the beggining of the next round
	 * @param player
	 */
	public void sitout(String player, String tableIdentifier);
	
	/**
	 * Adds the player to the waiting list
	 * @param player
	 */
	public void wait(String player, String tableIdentifier);
	
	/**
	 * Cancels the sitout player status, returns to PLAYING game
	 * @param player
	 */
	public void cancelSitout(String player, String tableIdentifier);
	
	/**
	 * Cancels the attempt to seat to the table, removes it from the queue
	 * @param player
	 */
	public void cancelQueue(String player, String tableIdentifier);
	
	/**
	 * Removes the player from the table at the beggining of the next round.
	 * Add the player to "leave" status
	 * @param player
	 */
	public void leave(String player, String tableIdentifier);

}

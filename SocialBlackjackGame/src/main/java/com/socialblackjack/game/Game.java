package com.socialblackjack.game;

import java.util.List;

import com.socialblackjack.entities.Player;

public interface Game {

	/**
	 * Returns the players that are playing at the current round
	 * @return
	 */
	public Integer getCurrentPlayers();
	
	/**
	 * Return true if all the seats are occupied, false otherwise
	 * @return
	 */
	public boolean isTableFull();
	
	/**
	 * Sits the player at random free position in the game
	 * Returns true if the player can sit, false otherwise
	 * @param player
	 */
	public void sitPlayer(Player player);
	
	/**
	 * Sits the player at position position
	 * @param player
	 * @param position
	 * @return
	 */
	public void sitPlayer(Player player, Integer position);
	
	/**
	 * Removes the player for the next round
	 * Important, this doesn't mean that the player won't appear in the GUI. The player
	 * could be at sittingout status. This only means that the player won't receive 
	 * cards at the next round
	 * @param player
	 * @return
	 */
	public boolean removePlayer(Player player);
	
	/**
	 * Sets the available options that a player has
	 * @param player
	 * @param options
	 */
	public void setGameOptions(Player player, List<GameOptionsEnumeration> options);
	
	/**
	 * Returns the available options that a player has
	 * @param player
	 * @param options
	 * @return 
	 */
	public List<GameOptionsEnumeration> getGameOptions(Player player);
	
	/**
	 * Deal the cards to the users that are playing this round
	 */
	public void deal();
	
	/**
	 * Returns the hand (or hands) which the player has
	 * @param player
	 * @return
	 */
	public List getHands(Player player);
}

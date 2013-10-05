package com.socialblackjack.game.api;

import java.util.List;

import com.socialblackjack.game.entities.GameTable;
import com.socialblackjack.game.entities.Player;

public interface GameTableApi {

	/**
	 * Returns all the tables that currently exists on the server
	 * @return
	 */
	public List<GameTable> getTables();
	
	/**
	 * Returns the table specified by the Identifier
	 * @param tableId The identifier of the table
	 * @return The Game Table
	 */
	public GameTable getTable(Integer tableId);
	
	/**
	 * Returns all the players that are currently in the table.
	 * It doesn't matter the status
	 * @param table The unique name identifier of the table
	 * @return A list of players who are currently in the table
	 */
	public List<Player> getPlayersInTable(String table);
	
	/**
	 * The player attempts to seat into the table. 
	 * If the player is already in the table nothing happens
	 * By default, it try to seat to play
	 * @param table The table identifier
	 * @param playerToken The player identifier
	 * @param seat The seat where the player want to seat
	 * @return True if everything was ok, false otherwise
	 */
	public Boolean joinPlayer(String table, String playerToken, Integer seat);
}

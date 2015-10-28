package com.socialblackjack.dao;

import java.util.List;

import com.socialblackjack.entities.Player;
import com.socialblackjack.entities.GameTable;

public interface GameTableDao {

	void createTable(GameTable table);
	
	GameTable getTable(String table);
	
	List<Player> getPlayers(String table);
	
	/**
	 * Remove all the players who are in LEAVING status
	 * @param table
	 */
	void removePlayersFromTable(String table);
	
	/**
	 * Sits all the players who are in SITTING status
	 * @param table
	 */
	void sitPlayers(String table);
	
	/**
	 * Modify the status of a specified player to WAITING
	 * @param playerToken
	 * @param table
	 */
	void addPlayerToWait(String playerToken, String table);
	
	/**
	 * Modify the state of the user, setting its state to SITTING
	 * @param player
	 * @param table
	 * @param seat The seat number identifier. 
	 * @throws InvalidSeatException if the seat does not specify any seat
	 */
	void addPlayerToPlay(Player player, GameTable table, Integer seat);
	
	/**
	 * The player is going to change its status to SITOUT at the end of the round
	 * @param playerId
	 * @param table
	 */
	void sitOutPlayer(Long playerId, String table);
	
	/**
	 * The player cancels its sittingout status
	 * @param playerToken
	 * @param table
	 */
	void cancelSitoutPlayer(String playerToken, String table);
	
	void cancelWaitingPlayer(String playerToken, String table);
	
	void removePlayerFromTable(String playerToken, String table);

	/**
	 * Return a list containing all the tables in the database
	 * @return
	 */
	List<GameTable> getTables();

	/**
	 * Return the GameTable identified by ID
	 * @param tableId
	 * @return
	 */
	GameTable getTableById(Integer tableId);
}

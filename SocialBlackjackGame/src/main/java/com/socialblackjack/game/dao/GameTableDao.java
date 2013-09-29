package com.socialblackjack.game.dao;

import java.util.List;

import com.socialblackjack.game.entities.Player;
import com.socialblackjack.game.entities.GameTable;
import com.socialblackjack.game.exceptions.InvalidSeatException;

public interface GameTableDao {

	public void createTable(GameTable table);
	
	public GameTable getTable(String table);
	
	public List<Player> getPlayers(String table);
	
	/**
	 * Remove all the players who are in LEAVING status
	 * @param table
	 */
	public void removePlayersFromTable(String table);
	
	/**
	 * Sits all the players who are in SITTING status
	 * @param table
	 */
	public void sitPlayers(String table);
	
	/**
	 * Modify the status of a specified player to WAITING
	 * @param playerToken
	 * @param table
	 */
	public void addPlayerToWait(String playerToken, String table);
	
	/**
	 * Modify the state of the user, setting its state to SITTING
	 * @param playerToken
	 * @param table
	 * @param seat The seat number identifier. 
	 * @throws InvalidSeatException if the seat does not specify any seat
	 */
	public void addPlayerToPlay(String playerToken, String table, Integer seat);
	
	/**
	 * The player is going to change its status to SITOUT at the end of the round
	 * @param playerToken
	 * @param table
	 */
	public void sitOutPlayer(String playerToken, String table);
	
	/**
	 * The player cancels its sittingout status
	 * @param playerToken
	 * @param table
	 */
	public void cancelSitoutPlayer(String playerToken, String table);
	
	public void cancelWaitingPlayer(String playerToken, String table);
	
	public void removePlayerFromTable(String playerToken, String table);

	/**
	 * Return a list containing all the tables in the database
	 * @return
	 */
	public List<GameTable> getTables();

	/**
	 * Return the GameTable identified by ID
	 * @param tableId
	 * @return
	 */
	public GameTable getTableById(Integer tableId);
}

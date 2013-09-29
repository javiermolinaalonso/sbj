package com.socialblackjack.game.service;

import java.util.List;

import com.socialblackjack.game.entities.Player;
import com.socialblackjack.game.entities.GameTable;

public interface TableService {

	public List<GameTable> getTables();
	/**
	 * Retrieves the specified table
	 * @param table
	 * @return
	 * @throws TableNotFoundException if the specified table does not exist
	 */
	public GameTable getTable(String table);
	
	/**
	 * Retrieves the specified table identified by id
	 * @param table
	 * @return
	 * @throws TableNotFoundException if the specified table does not exist
	 */
	public GameTable getTableById(Integer id);
	
	/**
	 * Retrieve all the players who are sitting at the table.
	 * Sit means that are in SITTING, PLAYING or SITTINGOUT
	 * @param table
	 * @return
	 */
	public List<Player> getPlayers(String table);
	
	/**
	 * Returns true if all the seats are occupied
	 * @param table
	 * @return
	 */
	public boolean isTableFull(String table);
	
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
	
	public String generateUniqueToken(String table, Player player, Long tst);
}

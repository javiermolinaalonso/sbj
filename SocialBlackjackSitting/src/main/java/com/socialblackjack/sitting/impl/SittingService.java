package com.socialblackjack.sitting.impl;

import com.socialblackjack.game.State;
import com.socialblackjack.game.entities.Player;
import com.socialblackjack.game.service.TableService;
import com.socialblackjack.sitting.CheckTableExist;
import com.socialblackjack.sitting.SittingSystemController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Calendar;

@Service
public class SittingService implements SittingSystemController, State{

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(SittingService.class);
	
	@Inject private TableService tableService;
	
	public SittingService() {
	}
	
	public void start(String table) {
		leave(table);
		sit(table);
	}

	/**
	 * Removes all the players that are in leaving status.
	 * Also removes from the game the players that are at sitting out status
	 */
	private void leave(String table) {
		tableService.removePlayersFromTable(table);
//		for(Player p : players.keySet()){
//			PlayerStatusEnumeration playerStatus = players.get(p);
//			if(playerStatus.equals(PlayerStatusEnumeration.LEAVING) || playerStatus.equals(PlayerStatusEnumeration.SITTINGOUT)){
//				game.removePlayer(p);
//				if(playerStatus.equals(PlayerStatusEnumeration.LEAVING)){
//					players.remove(p);
//				}
//			}
//		}
	}
	
	/**
	 * Sits all the players that are in SITTING status and who are at queue
	 */
	private void sit(String table) {
		tableService.sitPlayers(table);
//		for(Player p : players.keySet()){
//			if(players.get(p).equals(PlayerStatusEnumeration.SITTING)){
//				try{
//					game.sitPlayer(p);
//					players.put(p, PlayerStatusEnumeration.PLAYING);
//				}catch(Exception e){
//					attemptSeat(p, null);
//				}
//			}
//		}
//		
//		boolean addedPlayers = false;
//		while(!game.isTableFull() && queuePlayers.size() > 0){
//			sit(queuePlayers.poll());
//		}
//		if(addedPlayers)
//			sit();
	}
	
	//TODO Add annotation that checks that the tableIdentifier exists
	public String attemptSeat(Player player, @CheckTableExist String tableIdentifier, Integer seat) {
		boolean isFull = tableService.isTableFull(tableIdentifier);
		String playerToken = tableService.generateUniqueToken(tableIdentifier, player, Calendar.getInstance().getTimeInMillis());
		
		if(isFull){
			tableService.addPlayerToWait(playerToken, tableIdentifier);
		}else{
			tableService.addPlayerToPlay(playerToken, tableIdentifier, seat);
		}
		
		return playerToken;
	}

	public void sitout(String playerToken, String tableIdentifier) {
		tableService.sitOutPlayer(playerToken, tableIdentifier);
	}

	public void wait(String playerToken, String tableIdentifier) {
		tableService.addPlayerToWait(playerToken, tableIdentifier);
	}

	public void cancelSitout(String playerToken, String tableIdentifier) {
		tableService.cancelSitoutPlayer(playerToken, tableIdentifier);
	}
	
	public void cancelQueue(String playerToken, String tableIdentifier) {
		tableService.cancelWaitingPlayer(playerToken, tableIdentifier);
	}
	
	public void leave(String playerToken, String tableIdentifier) {
		tableService.removePlayerFromTable(playerToken, tableIdentifier);
	}
}

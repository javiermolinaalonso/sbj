package com.socialblackjack.game.impl;

import com.socialblackjack.core.Deck;
import com.socialblackjack.core.impl.DeckImpl;
import com.socialblackjack.game.Game;
import com.socialblackjack.game.entities.Player;
import com.socialblackjack.game.exceptions.InvalidSeatException;
import com.socialblackjack.game.exceptions.SeatOccupiedException;
import com.socialblackjack.game.exceptions.TableFullException;

public abstract class GameImpl implements Game {

	//TODO USE TABLE RULES
	public static final int MAX_PLAYERS = 6;
	public static final int N_DECKS = 6;
	
	protected Player[] seatPlayers;
	protected Deck deck;
	
	public GameImpl(){
		seatPlayers = new Player[MAX_PLAYERS];
		deck = new DeckImpl(N_DECKS);
	}
	
	public Integer getCurrentPlayers() {
		int count = 0;
		for(Player p : seatPlayers) {
			if(p != null)
				count++;
		}
		return count;
	}
	
	public boolean isTableFull(){
		return getCurrentPlayers() >= MAX_PLAYERS; //TODO Use table rules
	}

	public void sitPlayer(Player player) {
		if(isTableFull()){
			throw new TableFullException();
		}
		int i = 0;
		boolean cnt = false;
		do{
			try{
				cnt = false;
				sitPlayer(player, i);
			}catch(SeatOccupiedException soe){
				cnt = true;
				i++;
			}
		}while(cnt);
	}
	
	public void sitPlayer(Player player, Integer position){
		if(position > MAX_PLAYERS){
			throw new InvalidSeatException();
		}
		if(this.seatPlayers[position] != null){
			throw new SeatOccupiedException();
		}
		this.seatPlayers[position] = player;
	}

	public boolean removePlayer(Player player) {
		boolean removedFromTable = removeFromTable(player);
//		if(removedFromTable){
//			players.remove(player);
//		}
		return removedFromTable;
	}
	
	private boolean removeFromTable(Player player){
		boolean found = false;
		for(int i = 0; i < MAX_PLAYERS && !found; i++){
			if(player.equals(seatPlayers[i])){
				seatPlayers[i] = null;
				found = true;
			}
		}
		return found;
	}
	
	
}

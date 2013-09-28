package com.socialblackjack.game;

public enum PlayerStatusEnumeration {

	WAITING, //The players are in the queue list, when other player leaves the table it will attempt to seat 
	SITTING, //The player is going to be sit at the table
	PLAYING, //The player is playing
	SITTINGOUT, //The player is sitting out, it is sit at the table but he wont receive cards
	LEAVING, //The player is going to leave the table at the end of the round 
	WATCHING; //The player is watching the game
	
	private PlayerStatusEnumeration(){
		
	}
	public boolean isOccupingSeat(){
		return 
				this.equals(PlayerStatusEnumeration.PLAYING) || 
				this.equals(PlayerStatusEnumeration.SITTING) || 
				this.equals(PlayerStatusEnumeration.SITTINGOUT);
	}
}

package com.socialblackjack.game.entities;

import com.socialblackjack.game.PlayerStatusEnumeration;

public class Player {

	private String name;
	private Double coins;
	private PlayerStatusEnumeration status;
	
	public Player(){
		
	}
	
	public Player(String name, Double coins){
		this.name = name;
		this.coins = coins;
	}
	
	public void doTakeMoney(Double amount){
		this.coins -= amount;
	}
	
	public void doAddMoney(Double amount){
		this.coins += amount;
	}
	
	public Double getCoins() {
		return coins;
	}

	public String getName() {
		return name;
	}
	public PlayerStatusEnumeration getStatus() {
		return status;
	}
	public void setStatus(PlayerStatusEnumeration status) {
		this.status = status;
	}
	
}

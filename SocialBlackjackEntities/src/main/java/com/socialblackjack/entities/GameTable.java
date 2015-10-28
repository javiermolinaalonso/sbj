package com.socialblackjack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="game_table")
public class GameTable {

	private Integer id;
	private String name;
	private Double minimumBet;
	private Double maximumBet;
	private Integer maxPlayers;
	
	public GameTable(){
		super();
	}
	public GameTable(String name, Double minimumBet, Double maximumBet, Integer maxPlayers) {
		this();
		this.name = name;
		this.minimumBet = minimumBet;
		this.maximumBet = maximumBet;
		this.setMaxPlayers(maxPlayers);
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="minimum_bet")
	public Double getMinimumBet() {
		return minimumBet;
	}
	public void setMinimumBet(Double minimumBet) {
		this.minimumBet = minimumBet;
	}
	
	@Column(name="maximum_bet")
	public Double getMaximumBet() {
		return maximumBet;
	}
	public void setMaximumBet(Double maximumBet) {
		this.maximumBet = maximumBet;
	}

	@Column(name="max_players")
	public Integer getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(Integer maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
}

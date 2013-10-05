package com.socialblackjack.game.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.socialblackjack.game.PlayerStatusEnumeration;

@Entity
@Table(name="player")
public class Player {

	private Long id;
	private String firstName;
	private String lastName;
	private String nickname;
	private Double coins;
	private PlayerStatusEnumeration status;
	
	public Player(){
		
	}
	
	public Player(String firstName, String lastName, String nickname,
			Double coins) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickname = nickname;
		this.coins = coins;
	}

	public void doTakeMoney(Double amount){
		this.coins -= amount;
	}
	
	public void doAddMoney(Double amount){
		this.coins += amount;
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="coins")
	public Double getCoins() {
		return coins;
	}
	
	public void setCoins(Double coins) {
		this.coins = coins;
	}
	
	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="nickname")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public boolean equals(Object obj){
		Player p = (Player)obj;
		if(getId() != null) {
			return getId().equals(p.getId());
		}else{
			return false;
		}
	}
	@Transient
	public PlayerStatusEnumeration getStatus() {
		return status;
	}
	public void setStatus(PlayerStatusEnumeration status) {
		this.status = status;
	}

	
}

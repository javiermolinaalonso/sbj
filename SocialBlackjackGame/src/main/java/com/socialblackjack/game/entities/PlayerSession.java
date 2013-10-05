package com.socialblackjack.game.entities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

@Entity
@Table(name="player_session")
public class PlayerSession {

	private Long id;
	private String token;
	private Player player;
	private DateTime loginDate;
	private DateTime logoutDate;
	
	public PlayerSession(Player player) {
		super();
		this.player = player;
		this.loginDate = DateTime.now();
		this.token = this.generateToken();
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

	@Column(name="token")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@ManyToOne
	@JoinColumn(name="player_id")
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Column(name="login_date")
	public DateTime getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(DateTime loginDate) {
		this.loginDate = loginDate;
	}

	@Column(name="logout_date")
	public DateTime getLogoutDate() {
		return logoutDate;
	}

	public void setLogoutDate(DateTime logoutDate) {
		this.logoutDate = logoutDate;
	}

	@Transient
	private final String generateToken() {
		String strToHash = player.getFirstName() + player.getLastName() + player.getNickname() + player.getCoins() + player.toString() + this.loginDate.toString();
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(strToHash.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }

	        return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			//Won't happen...
		} catch (UnsupportedEncodingException e) {
			//Won't happen...
		}
		return null;
	}
	
	
}

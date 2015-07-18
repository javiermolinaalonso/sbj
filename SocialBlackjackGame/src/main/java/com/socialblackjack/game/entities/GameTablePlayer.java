package com.socialblackjack.game.entities;

import com.socialblackjack.game.PlayerStatusEnumeration;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_table_player")
public class GameTablePlayer {

	private Long id;
	private GameTable gameTable;
	private Player player;
	private LocalDateTime entryDate;
	private LocalDateTime exitDate;
	private Integer seat;
	private PlayerStatusEnumeration playerStatus;

	public GameTablePlayer() {

	}

	public GameTablePlayer(GameTable table, Player player, Integer seat) {
		this.gameTable = table;
		this.player = player;
		this.seat = seat;
		this.entryDate = LocalDateTime.now();
		this.playerStatus = PlayerStatusEnumeration.SITTING;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "game_table_id")
	public GameTable getGameTable() {
		return gameTable;
	}

	public void setGameTable(GameTable gameTable) {
		this.gameTable = gameTable;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Column(name = "entry_date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	public LocalDateTime getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
	}

	@Column(name = "exit_date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	public LocalDateTime getExitDate() {
		return exitDate;
	}

	public void setExitDate(LocalDateTime exitDate) {
		this.exitDate = exitDate;
	}

	@Column(name = "seat")
	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	@Column(name = "player_status")
	public PlayerStatusEnumeration getPlayerStatus() {
		return playerStatus;
	}

	public void setPlayerStatus(PlayerStatusEnumeration playerStatus) {
		this.playerStatus = playerStatus;
	}

}

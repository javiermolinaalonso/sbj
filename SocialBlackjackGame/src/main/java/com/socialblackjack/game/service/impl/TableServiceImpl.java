package com.socialblackjack.game.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialblackjack.game.PlayerStatusEnumeration;
import com.socialblackjack.game.dao.GameTableDao;
import com.socialblackjack.game.dao.PlayerDao;
import com.socialblackjack.game.entities.GameTable;
import com.socialblackjack.game.entities.Player;
import com.socialblackjack.game.enumerations.PlayerExceptionCodeEnum;
import com.socialblackjack.game.enumerations.TableExceptionCodeEnum;
import com.socialblackjack.game.exceptions.PlayerException;
import com.socialblackjack.game.exceptions.TableException;
import com.socialblackjack.game.impl.GameImpl;
import com.socialblackjack.game.service.TableService;
import com.socialblackjack.utils.StringUtils;

@Service
@Transactional
public class TableServiceImpl implements TableService {

	private static final Logger logger = Logger
			.getLogger(TableServiceImpl.class);

	@Inject
	private GameTableDao tableDao;
	@Inject
	private PlayerDao playerDao;

	public List<GameTable> getTables() {
		return tableDao.getTables();
	}

	public GameTable getTable(String table) {
		return tableDao.getTable(table);
	}

	public GameTable getTableById(Integer tableId) {
		return tableDao.getTableById(tableId);
	}

	public boolean isTableFull(String table) {
		int count = 0;
		List<Player> players = getPlayers(table);
		for (Player p : players) {
			PlayerStatusEnumeration playerStatus = p.getStatus();
			if (playerStatus.isOccupingSeat()) {
				count++;
			}
		}
		return count >= GameImpl.MAX_PLAYERS;
	}

	public List<Player> getPlayers(String table) {
		return tableDao.getPlayers(table);
	}

	public void removePlayersFromTable(String table) {
		tableDao.removePlayersFromTable(table);
	}

	public void sitPlayers(String table) {
		tableDao.sitPlayers(table);
	}

	public void addPlayerToWait(String playerToken, String table) {
		tableDao.addPlayerToWait(playerToken, table);
	}

	public void addPlayerToPlay(String playerToken, String table, Integer seat) {
		GameTable t = tableDao.getTable(table);
		if (t == null) {
			throw new TableException(TableExceptionCodeEnum.GT001);
		}
		Player player = playerDao.loadPlayer(playerToken);
		if (player == null) {
			throw new PlayerException(PlayerExceptionCodeEnum.P001);
		}
		// Is the player in the table?
		List<Player> playersInTable = getPlayers(table);
		if (playersInTable.contains(player)) {
			return;
		}
		tableDao.addPlayerToPlay(player, t, seat);
	}

	public void sitOutPlayer(String playerToken, String table) {
		tableDao.sitOutPlayer(playerToken, table);
	}

	public void cancelSitoutPlayer(String playerToken, String table) {
		tableDao.cancelSitoutPlayer(playerToken, table);
	}

	public void cancelWaitingPlayer(String playerToken, String table) {
		tableDao.cancelWaitingPlayer(playerToken, table);
	}

	public void removePlayerFromTable(String playerToken, String table) {
		tableDao.removePlayerFromTable(playerToken, table);
	}

	public String generateUniqueToken(String table, Player player, Long tst) {
		StringBuffer tokenBuffer = new StringBuffer();
		tokenBuffer.append(player.getFirstName());
		tokenBuffer.append(table);
		tokenBuffer.append(tst);
		String token = tokenBuffer.toString();
		try {
			return StringUtils.encodeBytes(token.getBytes(),
					StringUtils.HASHING_SHA256);
		} catch (Exception e) {
			logger.error("Error while generating the signature", e);
			return null;
		}

	}
}

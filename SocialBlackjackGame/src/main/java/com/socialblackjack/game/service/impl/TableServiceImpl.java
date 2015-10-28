package com.socialblackjack.game.service.impl;

import com.socialblackjack.dao.GameTableDao;
import com.socialblackjack.dao.PlayerDao;
import com.socialblackjack.entities.GameTable;
import com.socialblackjack.entities.Player;
import com.socialblackjack.entities.enums.PlayerStatusEnumeration;
import com.socialblackjack.dao.exceptions.code.PlayerExceptionCodeEnum;
import com.socialblackjack.dao.exceptions.code.TableExceptionCodeEnum;
import com.socialblackjack.dao.exceptions.PlayerException;
import com.socialblackjack.game.exceptions.TableException;
import com.socialblackjack.game.impl.GameImpl;
import com.socialblackjack.game.service.TableService;
import com.socialblackjack.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class TableServiceImpl implements TableService {

	private static final Logger logger = LoggerFactory
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
			throw new PlayerException(PlayerExceptionCodeEnum.PLAYER_NOT_EXISTS);
		}
		// Is the player in the table?
		List<Player> playersInTable = getPlayers(table);
		if (playersInTable.contains(player)) {
			return;
		}
		tableDao.addPlayerToPlay(player, t, seat);
	}

	public void sitOutPlayer(String playerToken, String table) {
		Player player = playerDao.loadPlayer(playerToken);
		if (player == null) {
			throw new PlayerException(PlayerExceptionCodeEnum.PLAYER_NOT_EXISTS);
		}
		tableDao.sitOutPlayer(player.getId(), table);
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

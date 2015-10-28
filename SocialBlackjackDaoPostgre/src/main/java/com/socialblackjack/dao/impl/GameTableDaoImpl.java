package com.socialblackjack.dao.impl;

import com.socialblackjack.dao.GameTableDao;
import com.socialblackjack.dao.exceptions.PlayerException;
import com.socialblackjack.dao.exceptions.code.PlayerExceptionCodeEnum;
import com.socialblackjack.entities.GameTable;
import com.socialblackjack.entities.GameTablePlayer;
import com.socialblackjack.entities.Player;
import com.socialblackjack.entities.enums.PlayerStatusEnumeration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class GameTableDaoImpl implements GameTableDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	public void createTable(GameTable table) {
	}

	public GameTable getTable(String table) {
		return (GameTable) sessionFactory.getCurrentSession().createQuery("From GameTable where name = '" + table + "'").uniqueResult();
	}

	public List<Player> getPlayers(String table) {
		String hql = 
				"Select p " +
				"From GameTablePlayer gtu " +
				"join gtu.gameTable as g " +
				"join gtu.player as p " +
				"where g.name = '"+table+"' and gtu.exitDate is null";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	public void removePlayersFromTable(String table) {
		// TODO Auto-generated method stub

	}

	public void sitPlayers(String table) {
		// TODO Auto-generated method stub

	}

	public void addPlayerToWait(String playerToken, String table) {
		// TODO Auto-generated method stub

	}

	public void addPlayerToPlay(Player player, GameTable table, Integer seat) {
		GameTablePlayer gtp = new GameTablePlayer(table, player, seat);
		sessionFactory.getCurrentSession().save(gtp);
	}

	public void sitOutPlayer(Long playerId, String table) {
        Session s = sessionFactory.getCurrentSession();
        String hql =
                "Select gtu " +
                        "From GameTablePlayer gtu " +
                        "join gtu.gameTable as g " +
                        "join gtu.player as p " +
                        "where " +
                            "g.name = '"+table+"' " +
                            "and p.id = " + playerId;
        GameTablePlayer gtp = (GameTablePlayer) s.createQuery(hql).uniqueResult();
        if(gtp == null) {
            throw new PlayerException(PlayerExceptionCodeEnum.PLAYER_NOT_IN_TABLE);
        }
        gtp.setPlayerStatus(PlayerStatusEnumeration.SITOUT);
        s.update(gtp);
    }

	public void cancelSitoutPlayer(String playerToken, String table) {
		// TODO Auto-generated method stub

	}

	public void cancelWaitingPlayer(String playerToken, String table) {
		// TODO Auto-generated method stub

	}

	public void removePlayerFromTable(String playerToken, String table) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	public List<GameTable> getTables() {
		return sessionFactory.getCurrentSession().createQuery("FROM GameTable").list();
	}

	@Override
	public GameTable getTableById(Integer tableId) {
		return (GameTable) sessionFactory.getCurrentSession().get(GameTable.class, tableId);
	}

}

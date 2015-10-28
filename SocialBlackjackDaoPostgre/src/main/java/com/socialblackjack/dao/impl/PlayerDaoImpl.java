package com.socialblackjack.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.socialblackjack.dao.PlayerDao;
import com.socialblackjack.entities.Player;

@Repository
@Transactional
public class PlayerDaoImpl implements PlayerDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public Player loadPlayer(String token) {
		return (Player) sessionFactory.getCurrentSession().createQuery("Select p From PlayerSession ps join ps.player p where ps.token = '" + token + "'").uniqueResult();
	}

}

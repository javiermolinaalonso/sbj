package com.socialblackjack.betting;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.socialblackjack.betting.exceptions.BettingException;
import com.socialblackjack.betting.impl.BettingSystemControllerImpl;
import com.socialblackjack.game.entities.Player;
import com.socialblackjack.game.impl.BlackjackGameImpl;

public class BettingTest {

	private BettingSystemControllerImpl betting;
	private List<Player> players;
	
	@Before
	public void setUp(){
		betting = new BettingSystemControllerImpl();
		betting.setGame(new BlackjackGameImpl());
		betting.setMaxBet(300d);
		betting.setMinBet(50d);
		players = new ArrayList<Player>();
		players.add(new Player("Galaxy", 500d));
		players.add(new Player("Sensation", 300d));
		players.add(new Player("Xperia", 60d));
		players.add(new Player("Desire", 1000d));
		players.add(new Player("Tattoo", 800d));
		players.add(new Player("Magic", 1000d));
		players.add(new Player("Wildfire", 1000d));
		players.add(new Player("Nexus", 1000d));
	}
	
	@Test
	public void testAddBet(){
		betting.startRound();
		betting.placeBet(players.get(4), 200d);
		betting.printStatus();
	}
	
	@Test
	public void testAddMultipleBet(){
		betting.startRound();
		for(Player player : players){
			try{
				betting.placeBet(player, 300d);
			}catch(BettingException be){
				System.out.println(be);
			}
		}
		betting.printStatus();
	}
}

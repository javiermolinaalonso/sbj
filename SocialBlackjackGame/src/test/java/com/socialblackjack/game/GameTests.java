package com.socialblackjack.game;

import com.socialblackjack.entities.Player;
import com.socialblackjack.game.impl.BlackjackGameImpl;
import com.socialblackjack.hand.BlackjackHand;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class GameTests {

	static BlackjackGame game;
	static ApplicationContext appContext;
	Player player;
	@BeforeClass
	public static void loadAppContext(){
		appContext = new ClassPathXmlApplicationContext("GameConfiguration.xml");
	}
	
	@Before
	public void setup(){
		game = (BlackjackGameImpl) appContext.getBean("game");
		player = new Player("Javi", "Molina", "Phoboss", 100d);
		game.sitPlayer(player);
		List<GameOptionsEnumeration> list = new ArrayList<GameOptionsEnumeration>();
		list.add(GameOptionsEnumeration.HIT);
		list.add(GameOptionsEnumeration.DOUBLE);
		list.add(GameOptionsEnumeration.INSURANCE);
		list.add(GameOptionsEnumeration.SPLIT);
		list.add(GameOptionsEnumeration.SURRENDER);
		list.add(GameOptionsEnumeration.STAND);
		game.setGameOptions(player, list);
		game.deal();
	}
	
	@Test
	public void testHit() {
		BlackjackHand hand = game.getHands(player).get(0);
		game.hit(hand, player);
	}
	
	
	@Test
	public void testStand() {
		BlackjackHand hand = game.getHands(player).get(0);
		game.stand(hand, player);
	}
	
	@Test
	public void testDouble() {
		BlackjackHand hand = game.getHands(player).get(0);
		game.doubleDown(hand, player);
	}
	
	@Test
	public void testSplit() {
		BlackjackHand hand = game.getHands(player).get(0);
		while(!hand.isSplittable()){
			setup();
			hand = game.getHands(player).get(0);
		}
		game.split(hand, player);
	}
	
	@Test
	public void testSurrender() {
		BlackjackHand hand = game.getHands(player).get(0);
		game.surrender(hand, player);
	}
	
	@Test
	public void testInsurance() {
		BlackjackHand hand = game.getHands(player).get(0);
		game.insurance(player);
	}
	
}

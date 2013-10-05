package com.socialblackjack.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.socialblackjack.game.entities.Player;
import com.socialblackjack.game.exceptions.InvalidSeatException;
import com.socialblackjack.game.exceptions.SeatOccupiedException;
import com.socialblackjack.game.exceptions.TableFullException;
import com.socialblackjack.game.impl.BlackjackGameImpl;

public class BlackjackGameTest {

	private BlackjackGame bj;
	
	@Before
	public void setup(){
		bj = new BlackjackGameImpl();
	}
	
	@Test
	public void testCreateGame() {
		assertEquals(Integer.valueOf(0), bj.getCurrentPlayers());
	}

	@Test
	public void testAddPlayer() {
		bj.sitPlayer(new Player("Javi", "Molina", "Phoboss", 100d));
		assertEquals(Integer.valueOf(1), bj.getCurrentPlayers());
	}
	
	@Test
	public void testTableFull() {
		bj.sitPlayer(new Player("Javi", "Molina", "Phoboss", 100d));
		bj.sitPlayer(new Player("Javi", "Molina", "Phoboss", 100d));
		bj.sitPlayer(new Player("Javi", "Molina", "Phoboss", 100d));
		bj.sitPlayer(new Player("Javi", "Molina", "Phoboss", 100d));
		bj.sitPlayer(new Player("Javi", "Molina", "Phoboss", 100d));
		bj.sitPlayer(new Player("Javi", "Molina", "Phoboss", 100d));
		assertTrue(bj.isTableFull());
	}

	@Test(expected=TableFullException.class)
	public void testTableFullExceeded(){
		testTableFull();
		bj.sitPlayer(new Player("Forever", "Alone", "Ouch", 1d));
	}
	
	@Test(expected=SeatOccupiedException.class)
	public void testTableSeatOccupied(){
		bj.sitPlayer(new Player("Javi", "Molina", "Phoboss", 100d), 1);
		bj.sitPlayer(new Player("Forever", "Alone", "Ouch", 1d), 1);
	}
}


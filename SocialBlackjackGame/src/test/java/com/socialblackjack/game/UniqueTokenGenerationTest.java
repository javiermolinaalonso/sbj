package com.socialblackjack.game;

import com.socialblackjack.entities.Player;
import com.socialblackjack.game.service.impl.TableServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UniqueTokenGenerationTest {

	private TableServiceImpl tableService;
	
	@Before
	public void setUp(){
		tableService = new TableServiceImpl();
	}
	
	@Test
	public void test() {
		Player player = new Player("Javi", "Molina", "Phoboss", 100d);
		String tabletoken = "fj983kasjfkzñckvj";
		Long tst = 234052893475l;
		
		assertEquals("33bd66fd114d6a58a082c1eb614667ccd4f22825313065fda6e4eeb2766b0d2d", tableService.generateUniqueToken(tabletoken, player, tst));
	}

}

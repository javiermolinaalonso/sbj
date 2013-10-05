package com.socialblackjack.game;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.socialblackjack.game.entities.Player;
import com.socialblackjack.game.service.impl.TableServiceImpl;

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
		
		assertEquals("6e56c99ff324ad670765c775c95a7e94c49b3b66afe1d8f757e9ee97bd1e09a5", tableService.generateUniqueToken(tabletoken, player, tst));
	}

}

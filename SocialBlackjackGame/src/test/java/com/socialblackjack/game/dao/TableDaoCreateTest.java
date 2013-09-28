package com.socialblackjack.game.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.socialblackjack.game.entities.GameTable;

public class TableDaoCreateTest extends AbstractTableDaoTest {

	@Test
	public void testCreateTable(){
		tableDao.createTable(new GameTable("Asgaard", 1d, 100d, 6));
		GameTable table = tableDao.getTable("Asgaard");
		assertEquals("Asgaard", table.getName());
		assertEquals(Float.valueOf(1f), table.getMinimumBet());
		assertEquals(Float.valueOf(100f), table.getMaximumBet());
	}
}

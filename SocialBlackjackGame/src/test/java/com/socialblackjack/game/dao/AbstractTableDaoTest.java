package com.socialblackjack.game.dao;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/MongoDBContext.xml"})
public abstract class AbstractTableDaoTest {

	@Inject protected GameTableDao tableDao;
}

package com.socialblackjack.dao.impl;

import com.socialblackjack.dao.GameTableDao;
import com.socialblackjack.dao.config.DaoConfig;
import com.socialblackjack.dao.exceptions.PlayerException;
import com.socialblackjack.dao.exceptions.code.PlayerExceptionCodeEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by javier on 26/10/15.
 */
@ContextConfiguration(classes = DaoConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class GameTableDaoImplTest {

    public static final String TABLE = "table";

    @Autowired
    private GameTableDao gameTableDaoImpl;

    @Test(expected = PlayerException.class)
    public void testUpdateNonExistingPlayerExpectPlayerException() {
        //Given

        //When
        try {
            gameTableDaoImpl.sitOutPlayer(12l, TABLE);
        } catch (PlayerException e) {
            //Then
            Assert.assertEquals(PlayerExceptionCodeEnum.PLAYER_NOT_IN_TABLE, e.getCode());
            throw e;
        }
    }
}

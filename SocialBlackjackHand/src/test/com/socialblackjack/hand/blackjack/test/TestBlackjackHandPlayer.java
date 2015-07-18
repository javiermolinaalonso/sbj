package com.socialblackjack.hand.blackjack.test;

import com.socialblackjack.core.Card;
import com.socialblackjack.core.Color;
import com.socialblackjack.core.Rank;
import com.socialblackjack.core.impl.CardImpl;
import com.socialblackjack.hand.impl.BlackjackHandPlayer;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestBlackjackHandPlayer {

    @Test
    public void testGivenBasicHandWhenGetHiddencardsExpectEmpty() {
        //Given
        BlackjackHandPlayer hand = new BlackjackHandPlayer();
        hand.addCard(CardImpl.of(Rank.ACE, Color.SPADE));
        hand.addCard(CardImpl.of(Rank.FOUR, Color.CLUB));

        //When
        Collection<Card> hideCards = hand.getHideCards();

        //Then
        assertTrue(hideCards.isEmpty());
    }

    @Test
    public void testGivenSoftCardWhenIsSoftExpectTrue() {
        //Given
        BlackjackHandPlayer hand = new BlackjackHandPlayer();
        hand.addCard(CardImpl.of(Rank.ACE, Color.SPADE));
        hand.addCard(CardImpl.of(Rank.FOUR, Color.CLUB));

        //Then
        assertTrue(hand.isSoft());
    }

    @Test
    public void testGivenNoSoftCardWhenIsSoftExpectFalse() {
        //Given
        BlackjackHandPlayer hand = new BlackjackHandPlayer();
        hand.addCard(CardImpl.of(Rank.KING, Color.SPADE));
        hand.addCard(CardImpl.of(Rank.FOUR, Color.CLUB));

        //Then
        assertFalse(hand.isSoft());
    }

    @Test
    public void testGivenBlackjackHandWhenIsBlackjackExpectTrue() {
        BlackjackHandPlayer hand = new BlackjackHandPlayer();
        hand.addCard(CardImpl.of(Rank.ACE, Color.SPADE));
        hand.addCard(CardImpl.of(Rank.JACK, Color.CLUB));

        //Then
        assertTrue(hand.isBlackjack());
    }

    @Test
    public void testGivenNoBlackjackHandWhenIsBlackjackExpectFalse() {
        BlackjackHandPlayer hand = new BlackjackHandPlayer();
        hand.addCard(CardImpl.of(Rank.KING, Color.SPADE));
        hand.addCard(CardImpl.of(Rank.JACK, Color.CLUB));

        //Then
        assertFalse(hand.isBlackjack());
    }

    @Test
    public void testGivenSplittableWhenIsSplitExpectTrue() {
        BlackjackHandPlayer hand = new BlackjackHandPlayer();
        hand.addCard(CardImpl.of(Rank.KING, Color.SPADE));
        hand.addCard(CardImpl.of(Rank.JACK, Color.CLUB));

        //Then
        assertTrue(hand.isSplittable());
    }

    @Test
    public void testGivenNoSplittableWhenIsSplitExpectFalse() {
        BlackjackHandPlayer hand = new BlackjackHandPlayer();
        hand.addCard(CardImpl.of(Rank.THREE, Color.SPADE));
        hand.addCard(CardImpl.of(Rank.JACK, Color.CLUB));

        //Then
        assertFalse(hand.isSplittable());
    }

    @Test
    public void testGivenNoBustHandExpectIsBustedFalse() {
        //Given
        BlackjackHandPlayer hand = new BlackjackHandPlayer();
        hand.addCard(CardImpl.of(Rank.THREE, Color.SPADE));
        hand.addCard(CardImpl.of(Rank.JACK, Color.CLUB));
        hand.addCard(CardImpl.of(Rank.ACE, Color.HEARTH));

        //Then
        assertFalse(hand.isBust());
    }

    @Test
    public void testGivenBustHandExpectIsBustedTrue() {
        //Given
        BlackjackHandPlayer hand = new BlackjackHandPlayer();
        hand.addCard(CardImpl.of(Rank.THREE, Color.SPADE));
        hand.addCard(CardImpl.of(Rank.JACK, Color.CLUB));
        hand.addCard(CardImpl.of(Rank.ACE, Color.HEARTH));
        hand.addCard(CardImpl.of(Rank.JACK, Color.SPADE));

        //Then
        assertTrue(hand.isBust());
    }

    @Test
    public void testGivenSoftHandBothValuesAreCorrect() {
        //Given
        BlackjackHandPlayer hand = new BlackjackHandPlayer();
        hand.addCard(CardImpl.of(Rank.THREE, Color.SPADE));
        hand.addCard(CardImpl.of(Rank.ACE, Color.HEARTH));

        //When
        List<Integer> values = hand.getValues();

        //Then
        assertEquals(2, values.size());
        assertEquals(4, values.get(0).intValue());
        assertEquals(14, values.get(1).intValue());
    }

    @Test
    public void testGivenSoftHandWithTwoAcesBothValuesAreCorrect() {
        //Given
        BlackjackHandPlayer hand = new BlackjackHandPlayer();
        hand.addCard(CardImpl.of(Rank.THREE, Color.SPADE));
        hand.addCard(CardImpl.of(Rank.ACE, Color.HEARTH));
        hand.addCard(CardImpl.of(Rank.ACE, Color.HEARTH));

        //When
        List<Integer> values = hand.getValues();

        //Then
        System.out.println(values);
        assertEquals(2, values.size());
        assertEquals(5, values.get(0).intValue());
        assertEquals(15, values.get(1).intValue());
    }
}

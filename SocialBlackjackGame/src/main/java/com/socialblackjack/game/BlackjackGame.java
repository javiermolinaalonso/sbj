package com.socialblackjack.game;

import java.util.List;

import com.socialblackjack.core.Hand;
import com.socialblackjack.game.annotations.CurrentHand;
import com.socialblackjack.game.annotations.CurrentPlayer;
import com.socialblackjack.entities.Player;
import com.socialblackjack.game.exceptions.IllegalActionException;
import com.socialblackjack.hand.BlackjackHand;

public interface BlackjackGame extends Game {

	public void hit(BlackjackHand hand, Player player) throws IllegalActionException;
	
	public void doubleDown(@CurrentHand BlackjackHand hand, @CurrentPlayer Player player) throws IllegalActionException;
	
	public List<BlackjackHand> split(@CurrentHand BlackjackHand hand, @CurrentPlayer Player player) throws IllegalActionException;
	
	public void surrender(@CurrentHand BlackjackHand hand, @CurrentPlayer Player player) throws IllegalActionException;
	
	public void stand(@CurrentHand BlackjackHand hand, @CurrentPlayer Player player) throws IllegalActionException;
	
	public void insurance(@CurrentPlayer Player player) throws IllegalActionException;

	/**
	 * This method is sctrictly necessary because it is called by the action aspect
	 * @param hand
	 * @param action
	 * @return
	 */
	public boolean isActionAvailable(Hand hand, GameOptionsEnumeration action);
	
	public List<GameOptionsEnumeration> updateHandOptions(Player player, BlackjackHand hand);
	
	public void updateDoneActions(BlackjackHand hand, GameOptionsEnumeration action);
	
	public List<BlackjackHand> getHands(Player player);
}

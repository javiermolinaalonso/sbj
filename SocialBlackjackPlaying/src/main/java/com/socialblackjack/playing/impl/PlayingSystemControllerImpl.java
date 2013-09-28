package com.socialblackjack.playing.impl;

import org.apache.log4j.Logger;

import com.socialblackjack.game.BlackjackGame;
import com.socialblackjack.game.State;
import com.socialblackjack.game.entities.Player;
import com.socialblackjack.hand.BlackjackHand;
import com.socialblackjack.playing.PlayingSystemController;

public class PlayingSystemControllerImpl implements PlayingSystemController, State {

	private static Logger logger = Logger.getLogger(PlayingSystemControllerImpl.class);
	
	private BlackjackGame game; //The same game that Sitting and Betting system have
	
	public void start() {
		game.deal();
	}

	public void hit(BlackjackHand hand, Player player) {
		game.hit(hand, player);
	}

	public void doubleDown(BlackjackHand hand, Player player) {
		game.doubleDown(hand, player);
	}

	public void split(BlackjackHand hand, Player player) {
		game.split(hand, player);
	}

	public void surrender(BlackjackHand hand, Player player) {
		game.surrender(hand, player);
	}

	public void stand(BlackjackHand hand, Player player) {
		game.stand(hand, player);
	}

	public void insurance(Player player) {
		game.insurance(player);
	}

}

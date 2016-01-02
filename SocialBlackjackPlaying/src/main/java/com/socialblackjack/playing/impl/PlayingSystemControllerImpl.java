package com.socialblackjack.playing.impl;

import com.socialblackjack.entities.Player;
import com.socialblackjack.game.BlackjackGame;
import com.socialblackjack.game.State;
import com.socialblackjack.hand.BlackjackHand;
import com.socialblackjack.playing.PlayingSystemController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayingSystemControllerImpl implements PlayingSystemController, State {

	private static Logger logger = LoggerFactory.getLogger(PlayingSystemControllerImpl.class);
	
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

	@Override
	public void start(String table) {

	}
}

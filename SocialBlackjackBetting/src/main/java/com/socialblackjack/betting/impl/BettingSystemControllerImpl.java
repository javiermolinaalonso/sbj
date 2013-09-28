package com.socialblackjack.betting.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.socialblackjack.betting.BettingSystemController;
import com.socialblackjack.betting.exceptions.MaximumBetExceededException;
import com.socialblackjack.betting.exceptions.MaximumPlayerCoinsExceededException;
import com.socialblackjack.betting.exceptions.MinimumBetExceededException;
import com.socialblackjack.game.Game;
import com.socialblackjack.game.GameOptionsEnumeration;
import com.socialblackjack.game.entities.Player;

@Component
public class BettingSystemControllerImpl implements BettingSystemController {

	private Game game; //The same game that Sitting system has
	private Double minBet;
	private Double maxBet;
	private HashMap<Player, Double> playersBet;
	
	public void startRound() {
		// TODO Auto-generated method stub
		playersBet = new HashMap<Player, Double>();
	}

	public void placeBet(Player player, Double bet)	throws MaximumBetExceededException, MinimumBetExceededException,
			MaximumPlayerCoinsExceededException {
		validateBet(player, bet);
		playersBet.put(player, bet);
		game.setGameOptions(player, evaluateAvailableOptions(player, bet));
	}

	private void validateBet(Player player, Double bet) throws MaximumPlayerCoinsExceededException, MaximumBetExceededException, MinimumBetExceededException{
		if(player.getCoins() < bet)
			throw new MaximumPlayerCoinsExceededException(bet, player.getCoins());
		
		if(bet > maxBet)
			throw new MaximumBetExceededException(bet, maxBet);
		
		if(bet < minBet)
			throw new MinimumBetExceededException(bet, minBet);
	}
	
	/**
	 * Evaluates the available options that the player will have while playing.
	 * This method doesn't take care of Rules, the game will remove that options that
	 * aren't allowed in the current game
	 * @param player
	 * @param bet
	 * @return
	 */
	private synchronized List<GameOptionsEnumeration> evaluateAvailableOptions(Player player, Double bet){
		List<GameOptionsEnumeration> options = GameOptionsEnumeration.getDefaultOptions();
		
		if(player.getCoins() >= bet * 1.5d){
			options.add(GameOptionsEnumeration.INSURANCE);
			if(player.getCoins() >= bet * 2){
				options.add(GameOptionsEnumeration.DOUBLE);
				options.add(GameOptionsEnumeration.SPLIT);
			}
		}
		
		return options;
	}
	
	public void cancelBet(Player player) {
		playersBet.remove(player);
	}

	public void printStatus(){
		System.out.println("Current bets: ");
		for(Player p : playersBet.keySet()){
			System.out.println("The player " + p.getName() + " has bet " + playersBet.get(p) + " and his options are: "+game.getGameOptions(p));
		}
	}

	
	
	/**
	 * For test purposes
	 */
	public void setGame(Game game){
		this.game = game;
	}
	public void setMaxBet(Double bet){
		this.maxBet = bet;
	}
	public void setMinBet(Double bet){
		this.minBet = bet;
	}
}

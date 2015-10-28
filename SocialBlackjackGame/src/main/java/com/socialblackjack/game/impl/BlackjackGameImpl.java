package com.socialblackjack.game.impl;

import com.socialblackjack.core.Card;
import com.socialblackjack.core.Hand;
import com.socialblackjack.game.BlackjackGame;
import com.socialblackjack.game.GameOptionsEnumeration;
import com.socialblackjack.game.annotations.BlackjackAction;
import com.socialblackjack.game.annotations.CurrentHand;
import com.socialblackjack.game.annotations.CurrentPlayer;
import com.socialblackjack.entities.Player;
import com.socialblackjack.game.exceptions.IllegalActionException;
import com.socialblackjack.hand.BlackjackHand;
import com.socialblackjack.hand.impl.BlackjackHandDealer;
import com.socialblackjack.hand.impl.BlackjackHandPlayer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

@Component
@Scope("prototype")
public class BlackjackGameImpl extends GameImpl implements BlackjackGame, Serializable {

	private static final long serialVersionUID = 9176687770453871109L;

	private Map<Player, List<BlackjackHand>> players;
	private Map<Player, List<GameOptionsEnumeration>> playersAvailableOptions;
	private Map<BlackjackHand, List<GameOptionsEnumeration>> handAvailableOptions;
	private Map<BlackjackHand, List<GameOptionsEnumeration>> historicalActions;
	private List<Player> insurances;
	
	private BlackjackHand dealerHand;

	//TODO We need to pass the table, the players and, in general, all the status
	public BlackjackGameImpl(){
		super();
		players = new HashMap<>();
		playersAvailableOptions = new HashMap<>();
		handAvailableOptions = new HashMap<>();
		historicalActions = new HashMap<>();
		insurances = new ArrayList<>();
	}
	
	public void deal() {
		if(getCurrentPlayers() > 0){
			prepareDeal();
			dealRound(); 
			updatePlayersOptions();
		}
	}

	private void prepareDeal(){
		//TODO Implement shuffling system that shuffle only if necessary
		deck.shuffle();
		players.clear(); //Remove any possible rubbish
		for(Player p : seatPlayers){
			if(p != null){
				List<BlackjackHand> hands = new ArrayList<>();
				BlackjackHand hand = new BlackjackHandPlayer();
				hands.add(hand);
				historicalActions.put(hand, new ArrayList<>());
				players.put(p, hands);
			}
		}
		dealerHand = new BlackjackHandDealer();
	}

	private void dealRound(){
		dealPlayers();
		dealerHand.addCard(deck.getFirst());
		dealPlayers();
		dealerHand.addCard(deck.getFirst());
	}

	private void dealPlayers(){
		for(Player p : seatPlayers){
			if(p != null){
				players.get(p).get(0).addCard(deck.getFirst());
			}
		}
	}

	@BlackjackAction(action = GameOptionsEnumeration.HIT)
	public void hit(@CurrentHand BlackjackHand hand, @CurrentPlayer Player player) throws IllegalActionException {
		hand.addCard(deck.getFirst());
	}

	@BlackjackAction(action = GameOptionsEnumeration.DOUBLE)
	public void doubleDown(@CurrentHand BlackjackHand hand, @CurrentPlayer Player player) throws IllegalActionException {
		hand.addCard(deck.getFirst());
		handAvailableOptions.put(hand, new ArrayList<GameOptionsEnumeration>());
	}

	@BlackjackAction(action = GameOptionsEnumeration.SPLIT)
	public List<BlackjackHand> split(@CurrentHand BlackjackHand hand, @CurrentPlayer Player player) throws IllegalActionException {
		Card firstCard = hand.getCards().get(0);
		Card secondCard = hand.getCards().get(1);
		
		BlackjackHand firstHand = new BlackjackHandPlayer();
		BlackjackHand secondHand = new BlackjackHandPlayer();
		firstHand.addCard(firstCard);
		firstHand.addCard(deck.getFirst());
		secondHand.addCard(secondCard);
		secondHand.addCard(deck.getFirst());
		
		List<BlackjackHand> hands = new ArrayList<BlackjackHand>();
		hands.add(firstHand);
		hands.add(secondHand);
		players.put(player, hands);
		
		handAvailableOptions.put(hand, new ArrayList<GameOptionsEnumeration>());
		List<GameOptionsEnumeration> newOptions = GameOptionsEnumeration.getDefaultOptions();
		
		//TODO Rules: Allowed double after split and split after split?
		handAvailableOptions.put(firstHand, newOptions);
		handAvailableOptions.put(secondHand, newOptions);
		historicalActions.put(firstHand, new ArrayList<GameOptionsEnumeration>());
		historicalActions.put(secondHand, new ArrayList<GameOptionsEnumeration>());
		return hands;
	}

	@BlackjackAction(action = GameOptionsEnumeration.SURRENDER)
	public void surrender(@CurrentHand BlackjackHand hand, @CurrentPlayer Player player) throws IllegalActionException {
		handAvailableOptions.put(hand, new ArrayList<GameOptionsEnumeration>());
	}

	@BlackjackAction(action = GameOptionsEnumeration.STAND)
	public void stand(@CurrentHand BlackjackHand hand, @CurrentPlayer Player player) throws IllegalActionException {
		handAvailableOptions.put(hand, new ArrayList<GameOptionsEnumeration>());		
	}

	@BlackjackAction(action = GameOptionsEnumeration.INSURANCE)
	public void insurance(@CurrentPlayer Player player) throws IllegalActionException {
		insurances.add(player);
	}

	public boolean isActionAvailable(Hand hand, GameOptionsEnumeration action){
		return handAvailableOptions.get(hand).contains(action);
	}
	
	private void updatePlayersOptions(){
		//We have to update the hands options, removing split or double for example
		for(Player player : players.keySet()){
			if(player != null) {
				BlackjackHand hand = players.get(player).get(0);
				List<GameOptionsEnumeration> options = getGameOptions(player);
				updateHandOptions(player, hand, options);
			}
		}
	}
	
	public List<GameOptionsEnumeration> updateHandOptions(Player player, BlackjackHand hand){
		List<GameOptionsEnumeration> options = handAvailableOptions.get(hand);
		return updateHandOptions(player, hand, options);
	}
	
	public void updateDoneActions(BlackjackHand hand, GameOptionsEnumeration action){
		historicalActions.get(hand).add(action);
	}
	
	private List<GameOptionsEnumeration> updateHandOptions(Player player, BlackjackHand hand, List<GameOptionsEnumeration> options){
		Iterator<GameOptionsEnumeration> it = options.iterator();
		while(it.hasNext()){
			GameOptionsEnumeration option = it.next();
			Card dealerCard = dealerHand.getShowableCards().iterator().next();
			if(GameOptionsEnumeration.SPLIT.equals(option) && !hand.isSplittable()){
				it.remove();
			}else if(GameOptionsEnumeration.INSURANCE.equals(option) && (!dealerCard.getRank().equals("A") || insurances.contains(player))){
				it.remove();
			}else if((GameOptionsEnumeration.DOUBLE.equals(option) || GameOptionsEnumeration.SURRENDER.equals(option)) && hand.getCards().size()>2){
				it.remove();
			}else if(hand.getHigherValue() > BlackjackHand.MAX_VALUE){
				it.remove();
			}
		}
		handAvailableOptions.put(hand, options);
		return options;
	}

	public List<BlackjackHand> getHands(Player player) {
		return players.get(player);
	}
	
	public void setGameOptions(Player player, List<GameOptionsEnumeration> options) {
		playersAvailableOptions.put(player, options);
	}

	public List<GameOptionsEnumeration> getGameOptions(Player player) {
		return playersAvailableOptions.get(player);
	}

}

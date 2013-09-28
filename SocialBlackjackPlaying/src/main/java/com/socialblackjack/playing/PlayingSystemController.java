package com.socialblackjack.playing;

import com.socialblackjack.game.entities.Player;
import com.socialblackjack.hand.BlackjackHand;

public interface PlayingSystemController {

	public void hit(BlackjackHand hand, Player player);
	
	public void doubleDown(BlackjackHand hand, Player player);
	
	public void split(BlackjackHand hand, Player player);
	
	public void surrender(BlackjackHand hand, Player player);
	
	public void stand(BlackjackHand hand, Player player);
	
	public void insurance(Player player);
}

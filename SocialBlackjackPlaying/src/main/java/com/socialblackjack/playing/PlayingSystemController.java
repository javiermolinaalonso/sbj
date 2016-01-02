package com.socialblackjack.playing;

import com.socialblackjack.entities.Player;
import com.socialblackjack.hand.BlackjackHand;

public interface PlayingSystemController {

	void hit(BlackjackHand hand, Player player);
	
	void doubleDown(BlackjackHand hand, Player player);
	
	void split(BlackjackHand hand, Player player);
	
	void surrender(BlackjackHand hand, Player player);
	
	void stand(BlackjackHand hand, Player player);
	
	void insurance(Player player);
}

package com.socialblackjack.sitting.impl;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.socialblackjack.core.exceptions.SocialBlackjackException;
import com.socialblackjack.sitting.impl.domain.DeniedRest;
import com.socialblackjack.sitting.impl.domain.PlayerWeb;

@Controller
@RequestMapping(value="/rest/v01")
public class SittingEndpointRest {

	@Inject private SittingService sittingService;
	
	public SittingEndpointRest(){
		super();
	}
	
	@RequestMapping(value="/table/{table}/sit/{seatId}")
	public @ResponseBody PlayerWeb attemptSeat(
							@PathVariable 	String tableIdentifier,
							@PathVariable	String seatIdentifier,
							@RequestBody 	PlayerWeb player){
		String playerToken = sittingService.attemptSeat(player, tableIdentifier, Integer.valueOf(seatIdentifier));
		player.setUniqueToken(playerToken);
		return player;
		
	}
	
	@ExceptionHandler({SocialBlackjackException.class})
	@ResponseStatus(HttpStatus.CONFLICT)
	public @ResponseBody DeniedRest socialBlackjackExceptionHandler(){
		return new DeniedRest();
	}
}

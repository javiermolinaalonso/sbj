package com.socialblackjack.game.api;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialblackjack.game.entities.GameTable;
import com.socialblackjack.game.entities.Player;
import com.socialblackjack.game.service.TableService;

@Controller 
@RequestMapping("/v01/rest/")
public class GameTableRestApi {

	@Inject private TableService tableService;
	
	@RequestMapping(value="gametables", method=RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<GameTable> getTables(){
		return tableService.getTables();
	}
	
	@RequestMapping(value="gametables/{gameTableId}", method=RequestMethod.GET, produces = "application/json")
	public @ResponseBody GameTable getTables(@PathVariable("gameTableId") Integer tableId){
		return tableService.getTableById(tableId);
	}
	
	@RequestMapping(value="gametables/{gameTableStr}/players", method=RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Player> getPlayersInTable(@PathVariable("gameTableStr") String table){
		return tableService.getPlayers(table);
	}
}

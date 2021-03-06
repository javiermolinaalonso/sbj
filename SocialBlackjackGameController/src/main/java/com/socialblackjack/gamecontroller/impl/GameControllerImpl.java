package com.socialblackjack.gamecontroller.impl;

import com.socialblackjack.entities.GameTable;
import com.socialblackjack.entities.Player;
import com.socialblackjack.game.service.TableService;
import com.socialblackjack.gamecontroller.GameController;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/v01/rest/")
public class GameControllerImpl implements GameController {

    @Inject
    private TableService tableService;

    @RequestMapping(value="gametables", method= RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<GameTable> getTables(){
        return tableService.getTables();
    }

    @RequestMapping(value="gametables/{gameTableId}", method=RequestMethod.GET, produces = "application/json")
    public @ResponseBody GameTable getTable(@PathVariable("gameTableId") Integer tableId){
        return tableService.getTableById(tableId);
    }

    @RequestMapping(value="gametables/{gameTableStr}/players", method=RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Player> getPlayersInTable(@PathVariable("gameTableStr") String table){
        return tableService.getPlayers(table);
    }

    @Override
    @RequestMapping(value="gametables/{gameTableStr}/seat/{seatId}", method=RequestMethod.GET, produces = "application/json")
    public @ResponseBody Boolean joinPlayer(@PathVariable("gameTableStr") String table, @RequestParam("token") String playerToken, @PathVariable("seatId") Integer seat) {
        tableService.addPlayerToPlay(playerToken, table, seat);
        return true;
    }

}

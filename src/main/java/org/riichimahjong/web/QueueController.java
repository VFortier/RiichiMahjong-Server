package org.riichimahjong.web;

import java.util.ArrayList;
import java.util.List;

import org.riichimahjong.domain.Game;
import org.riichimahjong.domain.Player;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("queue")
public class QueueController {
	
	private List<Player> playersInQueue = new ArrayList<>();

	@CrossOrigin(origins = "http://localhost:4200")		// TODO: Move to controller level
	@RequestMapping(value="find", method=RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void findGame(@RequestBody Player player) {
		playersInQueue.add(player);
		matchPlayers();
    }

	private void matchPlayers() {
		if (playersInQueue.size() >= 4) {
			List<Player> playersForGame = playersInQueue.subList(0, 4);
			Game game = new Game(new ArrayList<Player>(playersForGame));
			playersForGame.clear();
		}
	}
}

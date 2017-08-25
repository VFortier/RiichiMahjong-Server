package org.riichimahjong.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<Player> players = new ArrayList<>();

	public Game(List<Player> players) {
		this.players = players;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
}

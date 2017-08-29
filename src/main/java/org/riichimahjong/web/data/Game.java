package org.riichimahjong.web.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Game implements Serializable {
	private static final long serialVersionUID = 4808049777060946598L;
	private List<Player> players = new ArrayList<>();

	public Game(List<Player> players) {
		this.players = players;
	}

	@JsonSerialize()
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
}

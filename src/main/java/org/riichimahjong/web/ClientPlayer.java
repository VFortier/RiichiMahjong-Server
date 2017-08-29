package org.riichimahjong.web;

import org.riichimahjong.web.data.Player;

import com.corundumstudio.socketio.SocketIOClient;

public class ClientPlayer {
	SocketIOClient client;
	Player player;
	
	public ClientPlayer(SocketIOClient client, Player player) {
		super();
		this.client = client;
		this.player = player;
	}
	
	public SocketIOClient getClient() {
		return client;
	}
	public Player getPlayer() {
		return player;
	}
}

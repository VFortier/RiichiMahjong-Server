package org.riichimahjong.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.riichimahjong.web.data.Game;
import org.riichimahjong.web.data.Player;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;

@Configuration
@ComponentScan("org.riichimahjong")
@PropertySource("application-dev.properties") // TODO: automate env
public class SocketServer {
	
	@Value("${client.url}")
	private final String clientUrl = "";
	
	private Map<UUID, ClientPlayer> playersInQueue = new LinkedHashMap<>();

    @Bean(name="webSocketServer")
    public SocketIOServer webSocketServer() {

        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname("localhost"); // TODO
        config.setPort(9092);  // TODO
        // config.setOrigin("localhost:4200");	// TODO

        final SocketIOServer server = new SocketIOServer(config);
        
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
            	System.out.println("meow");
            }
        });
        
        server.addEventListener("find_game", Player.class, new DataListener<Player>() {
            @Override
            public void onData(SocketIOClient client, Player player, AckRequest ackRequest) {
            	
            	UUID sessionId = client.getSessionId();
            	// TODO: Check if client is already in playersInQueue
            	playersInQueue.put(sessionId, new ClientPlayer(client, player));
	            	
	       		if (playersInQueue.size() >= 4) {
	       			Iterator<Entry<UUID, ClientPlayer>> playersIterator = playersInQueue.entrySet().iterator();
	       			List<ClientPlayer> playersForGame = new ArrayList<>();
	       			
	       			for (int i = 0; i < 4; i++) {
	       				Entry<UUID, ClientPlayer> playerEntry = playersIterator.next();
	       			    playersForGame.add(playerEntry.getValue());
	       			    // TODO: remove from map
	       			}
	       			
	       			for (ClientPlayer clientPlayer : playersForGame) {
	       				clientPlayer.getClient().sendEvent("game_found", "{ \"meow\": \"woof\" }");
	       			}
	    		}
            }
        });

        return server;

    }
}

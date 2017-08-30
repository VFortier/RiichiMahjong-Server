package org.riichimahjong;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;

@Component
public class Bootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

    @Autowired
    private SocketIOServer server;

    @PostConstruct
    public void start() {
        LOG.info("starting server");
        server.start();
    }

    @PreDestroy
    public void stop() {
        LOG.info("stopping server");
        server.stop();
    }

}

package org.riichimahjong;

import org.riichimahjong.web.SocketServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Application {
    public static void main(String[] args) {
        AbstractApplicationContext cxt = new AnnotationConfigApplicationContext(SocketServer.class);
        cxt.registerShutdownHook();
    }
}

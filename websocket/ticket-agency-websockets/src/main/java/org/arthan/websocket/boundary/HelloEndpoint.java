package org.arthan.websocket.boundary;

import javax.websocket.EndpointConfig;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by Артур on 24.12.2015.
 * Next to Ufa.
 */

@ServerEndpoint("/hello")
public class HelloEndpoint {

    @OnOpen
    public void open(Session session, EndpointConfig config) throws IOException {
        session.getBasicRemote().sendText("Hi!");
    }
}

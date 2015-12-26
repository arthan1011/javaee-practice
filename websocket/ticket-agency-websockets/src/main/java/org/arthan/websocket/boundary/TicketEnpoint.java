package org.arthan.websocket.boundary;

import org.arthan.websocket.control.SessionRegistry;
import org.arthan.websocket.entity.Seat;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Артур on 26.12.2015.
 * Next to Ufa.
 */

@ServerEndpoint("/tickets")
public class TicketEnpoint {
    @Inject
    SessionRegistry sessionRegistry;

    @OnOpen
    public void open(Session session, EndpointConfig config) {
        sessionRegistry.add(session);
    }

    @OnClose
    public void close(Session session, EndpointConfig config) {
        sessionRegistry.remove(session);
    }

    public void send(@Observes Seat seat) {
        sessionRegistry.getAll().forEach(session ->
                        session.getAsyncRemote().sendText(toJson(seat))
        );
    }

    public String toJson(Seat seat) {
        final JsonObject jsonObject = Json.createObjectBuilder()
                .add("id", seat.getId())
                .add("booked", seat.isBooked())
                .build();
        return jsonObject.toString();
    }
}

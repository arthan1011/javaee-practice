package org.arthan.websocket.control;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.websocket.Session;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Артур on 26.12.2015.
 * Next to Ufa.
 */

@Singleton
public class SessionRegistry {

    private final Set<Session> sessions = new HashSet<>();

    @Lock(LockType.READ)
    public Collection<Session> getAll() {
        return Collections.unmodifiableCollection(sessions);
    }

    @Lock(LockType.WRITE)
    public void add(Session session) {
        sessions.add(session);
    }

    @Lock(LockType.WRITE)
    public void remove(Session session) {
        sessions.remove(session);
    }
}

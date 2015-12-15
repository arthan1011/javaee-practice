package org.arthan.cdi.controller;

import org.arthan.cdi.model.Seat;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by ashamsiev on 11.12.2015
 */

@Startup
@Singleton
@AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
public class TheatreBox implements Serializable {

    @Inject
    private Logger logger = Logger.getLogger(TheatreBox.class);

    private Map<Integer, Seat> seats;

    @Inject
    private Event<Seat> seatEvent;

    @PostConstruct
    public void setupTheatre() {
        seats = new HashMap<>();
        int id = 0;
        for (int i = 0; i < 5; i++) {
            addSeat(new Seat(++id, "Stalls", 40));
            addSeat(new Seat(++id, "Circle", 20));
            addSeat(new Seat(++id, "Balcony", 10));
        }
        logger.info("Seats map constructed.");
    }

    private void addSeat(Seat seat) {
        seats.put(seat.getId(), seat);
    }

    @Lock(LockType.READ)
    public Collection<Seat> getSeats() {
        return Collections.unmodifiableCollection(seats.values());
    }

    @Lock(LockType.READ)
    public int getSeatPrice(int seatID) {
        return getSeat(seatID).getPrice();
    }

    @Lock(LockType.WRITE)
    public void buyTicket(int seatID) {
        final Seat seat = seats.get(seatID);
        final Seat bookedSeat = seat.getBookedSeat();
        addSeat(bookedSeat);

        seatEvent.fire(bookedSeat);
    }

    @Lock(LockType.READ)
    private  Seat getSeat(int seatID) {
        return seats.get(seatID);
    }
}

package org.arthan.ejb.control;

import org.arthan.ejb.exceptions.NoSuchSeatException;
import org.arthan.ejb.exceptions.SeatBookedException;
import org.arthan.ejb.model.Seat;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
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
public class TheatreBox {
    private static final Logger logger = Logger.getLogger(TheatreBox.class);
    private static final long DURATION = TimeUnit.SECONDS.toMillis(7);

    private Map<Integer, Seat> seats;

    @Resource
    TimerService timerService;

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

    public void createTimer() {
        timerService.createSingleActionTimer(DURATION, new TimerConfig());
    }

    public void timeout(Timer timer) {
        logger.info("Re-building Theatre Map");
        setupTheatre();
    }

    private void addSeat(Seat seat) {
        seats.put(seat.getId(), seat);
    }

    @Lock(LockType.READ)
    public Collection<Seat> getSeats() {
        return Collections.unmodifiableCollection(seats.values());
    }

    @Lock(LockType.READ)
    public int getSeatPrice(int seatID) throws NoSuchSeatException {
        return getSeat(seatID).getPrice();
    }

    @Lock(LockType.WRITE)
    public void buyTicket(int seatID) throws SeatBookedException, NoSuchSeatException {
        Seat seat = seats.get(seatID);
        if (seat.isBooked()) {
            throw new SeatBookedException("Seat " + seatID + " already booked!");
        }
        addSeat(seat.getBookedSeat());
    }

    @Lock(LockType.READ)
    private  Seat getSeat(int seatID) throws NoSuchSeatException {
        final Seat seat = seats.get(seatID);
        if (seat == null) {
            throw new NoSuchSeatException("Seat " + seatID + " does not exists!");
        }
        return seat;
    }
}

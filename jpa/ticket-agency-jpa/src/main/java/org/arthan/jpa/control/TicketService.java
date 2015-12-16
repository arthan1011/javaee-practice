package org.arthan.jpa.control;

import org.arthan.jpa.entity.Seat;
import org.arthan.jpa.entity.SeatType;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by ashamsiev on 16.12.2015
 */

@Stateless
public class TicketService {

    @Inject
    Logger logger;
    @Inject
    SeatTypeDao seatTypeDao;
    @Inject
    Event<SeatType> seatTypeEvent;
    @Inject
    SeatDao seatDao;
    @Inject
    Event<Seat> seatEvent;

    public void createSeatType(SeatType seatType) {
        logger.info("Registering " + seatType.getDescription());
        seatTypeDao.persist(seatType);
        seatTypeEvent.fire(seatType);
    }

    public void createTheatre(List<SeatType> seatTypes) {
        for (SeatType seatType : seatTypes) {
            for (int i = 0; i < seatType.getQuantity(); i++) {
                final Seat seat = new Seat();
                seat.setBooked(false);
                seat.setType(seatType);
                seatDao.persist(seat);
            }
        }
    }

    public void bookSeat(long seatID) {
        Seat seat = seatDao.find(seatID);
        seat.setBooked(true);
        seatDao.persist(seat);
        seatEvent.fire(seat);
    }

    public void doCleanUp() {
        seatDao.deleteAll();
        seatTypeDao.deleteAll();
    }
}

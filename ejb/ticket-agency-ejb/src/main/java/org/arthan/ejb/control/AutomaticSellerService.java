package org.arthan.ejb.control;

import org.arthan.ejb.exceptions.NoSuchSeatException;
import org.arthan.ejb.exceptions.SeatBookedException;
import org.arthan.ejb.model.Seat;
import org.jboss.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.*;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by ashamsiev on 14.12.2015
 */

@Stateless
public class AutomaticSellerService {

    private static final Logger logger = Logger.getLogger(AutomaticSellerService.class);

    @EJB
    private TheatreBox theatreBox;

    @Resource
    private TimerService timerService;

    @Schedule(hour = "*", minute = "*/1", persistent = false)
    public void automaticCustomer() throws NoSuchSeatException {
        final Optional<Seat> seatOptional = findFreeSeat();
        if (!seatOptional.isPresent()) {
            cancelTimers();
            logger.info("Scheduler gone!");
            logger.warn("No more seats!");
            return;
        }

        final Seat seat = seatOptional.get();

        try {
            theatreBox.buyTicket(seat.getId());
        } catch (SeatBookedException e) {
            // seat was already booked
        }

        logger.info("Somebody just booked seat #" + seat.getId());
    }

    private void cancelTimers() {
        timerService.getTimers()
                .stream()
                .forEach(Timer::cancel);
    }

    private Optional<Seat> findFreeSeat() {
        Collection<Seat> seats = theatreBox.getSeats();
        return seats
                .stream()
                .filter(s -> !s.isBooked())
                .findAny();
    }
}

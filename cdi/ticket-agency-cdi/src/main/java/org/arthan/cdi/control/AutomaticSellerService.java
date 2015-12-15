package org.arthan.cdi.control;

import org.arthan.cdi.controller.TheatreBox;
import org.arthan.cdi.model.Seat;
import org.jboss.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by ashamsiev on 14.12.2015
 */

@Stateless
public class AutomaticSellerService {

    @Inject
    private Logger logger;

    @Inject
    private TheatreBox theatreBox;

    @Resource
    private TimerService timerService;

    @Schedule(hour = "*", minute = "*", second = "*/30", persistent = false)
    public void automaticCustomer() {
        final Optional<Seat> seatOptional = findFreeSeat();
        if (!seatOptional.isPresent()) {
            cancelTimers();
            logger.info("Scheduler gone!");
            logger.warn("No more seats!");
            return;
        }

        final Seat seat = seatOptional.get();

        theatreBox.buyTicket(seat.getId());

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

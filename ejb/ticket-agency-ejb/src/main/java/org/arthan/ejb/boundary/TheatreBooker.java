package org.arthan.ejb.boundary;

import org.arthan.ejb.control.TheatreBox;
import org.arthan.ejb.exceptions.NoSuchSeatException;
import org.arthan.ejb.exceptions.NotEnoughMoneyException;
import org.arthan.ejb.exceptions.SeatBookedException;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.util.concurrent.TimeUnit;

/**
 * Created by ashamsiev on 14.12.2015
 */

@Stateful
@Remote(TheatreBookerRemote.class)
@AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
public class TheatreBooker implements TheatreBookerRemote {

    private static final Logger logger = Logger.getLogger(TheatreBooker.class);

    @EJB
    private TheatreBox theatreBox;
    private int money;

    @PostConstruct
    public void createCustomer() {
        this.money = 100;
    }

    @Override
    public int getAccountBalance() {
        return money;
    }

    @Override
    public String bookSeat(int seatID) throws NoSuchSeatException, SeatBookedException, NotEnoughMoneyException {
        final int seatPrice = theatreBox.getSeatPrice(seatID);
        if (seatPrice > money) {
            throw new NotEnoughMoneyException("You don't have enough money to buy seat #" + seatID);
        }
        theatreBox.buyTicket(seatID);
        money -= seatPrice;

        logger.infov("Seat #{0} booked.", seatID);

        return "Seat booked.";
    }
}

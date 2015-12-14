package org.arthan.ejb.boundary;

import org.arthan.ejb.exceptions.NoSuchSeatException;
import org.arthan.ejb.exceptions.NotEnoughMoneyException;
import org.arthan.ejb.exceptions.SeatBookedException;

/**
 * Created by ashamsiev on 14.12.2015
 */
public interface TheatreBookerRemote {

    public int getAccountBalance();

    public String bookSeat(int seatID) throws NoSuchSeatException, SeatBookedException, NotEnoughMoneyException;
}

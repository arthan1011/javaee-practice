package org.arthan.cdi.controller;

import org.arthan.cdi.annotations.NamedView;
import org.arthan.cdi.model.Seat;

import javax.enterprise.event.Observes;
import java.io.Serializable;

/**
 * Created by ashamsiev on 15.12.2015
 */

@NamedView
public class BookingRecord implements Serializable {

    private int bookingCount = 0;

    public int getBookingCount() {
        return bookingCount;
    }

    public void bookEvent(@Observes final Seat seat) {
        bookingCount++;
    }

}

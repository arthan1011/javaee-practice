package org.arthan.cdi.controller;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 * Created by ashamsiev on 15.12.2015
 */

@Model
public class PollerBean {

    @Inject
    TheatreBox theatreBox;

    public boolean isPollingActive() {
        return areFreeSeatsAvailable();
    }

    private boolean areFreeSeatsAvailable() {
        return theatreBox.getSeats()
                .stream()
                .anyMatch(seat -> !seat.isBooked());
    }
}

package org.arthan.cdi.controller;

import com.google.common.collect.Lists;
import org.arthan.cdi.model.Seat;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

/**
 * Created by ashamsiev on 15.12.2015
 */

@Model
public class TheatreInfo {

    @Inject
    TheatreBox theatreBox;

    Collection<Seat> seats;

    @PostConstruct
    public void retrieveAllSeatsOrderByName() {
        seats = theatreBox.getSeats();
    }

    @Produces
    @Named
    public Collection<Seat> getSeats() {
        return Lists.newArrayList(seats);
    }

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Seat member) {
        retrieveAllSeatsOrderByName();
    }
}

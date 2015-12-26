package org.arthan.websocket.controller;

import org.arthan.websocket.entity.Seat;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        List<Seat> resultList = new ArrayList<>();
        resultList.addAll(seats);
        return resultList;
    }

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Seat member) {
        retrieveAllSeatsOrderByName();
    }
}

package org.arthan.ejb.boundary;

import org.arthan.ejb.control.TheatreBox;
import org.arthan.ejb.model.Seat;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Collection;

/**
 * Created by ashamsiev on 14.12.2015
 */

@Stateless
@Remote(TheatreInfoRemote.class)
public class TheatreInfo implements TheatreInfoRemote {

    @EJB
    private TheatreBox theatreBox;

    @Override
    public String printSeatList() {
        final Collection<Seat> seats = theatreBox.getSeats();
        final StringBuilder sb = new StringBuilder();
        for (Seat seat : seats) {
            sb.append(seat);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}

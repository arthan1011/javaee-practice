package org.arthan.ws.boundary;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by Артур on 21.12.2015.
 * Next to Ufa.
 */

@WebService
public interface TicketWebService {

    List<SeatDto> getSeats();

    void bookSeat(int seatID);
}

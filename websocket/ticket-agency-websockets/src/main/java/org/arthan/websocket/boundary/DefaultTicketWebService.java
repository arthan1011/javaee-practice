package org.arthan.websocket.boundary;

import org.arthan.websocket.controller.TheatreBox;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Артур on 21.12.2015.
 * Next to Ufa.
 */

@WebService(
        targetNamespace = "http://www.arthan.org/",
        serviceName = "TicketWebService"
)
public class DefaultTicketWebService implements TicketWebService {

    @Inject
    private TheatreBox theatreBox;

    @WebMethod
    @WebResult(name = "listSeats")
    @Override
    public List<SeatDto> getSeats() {
        return theatreBox.getSeats()
                .stream()
                .map(SeatDto::fromSeat)
                .collect(Collectors.toList());
    }

    @WebMethod
    @Override
    public void bookSeat(@WebParam(name = "seatID") final int seatID) {
        theatreBox.buyTicket(seatID);
    }
}

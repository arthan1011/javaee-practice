package org.arthan.ws.webservice;

import org.arthan.ws.boundary.SeatDto;
import org.arthan.ws.boundary.TicketWebService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Артур on 22.12.2015.
 * Next to Ufa.
 */
public class TicketWebServiceTestApplication {

    private static final Logger logger = Logger.getLogger(TicketWebServiceTestApplication.class.getName());

    public static void main(String[] args) throws MalformedURLException {
        final int seatID = 6;
        logger.info("Test SOAP Web Service");
        final URL wsldURL = new URL("http://localhost:8080/ticket-agency-ws/TicketWebService?wsdl");
        final QName SERVICE_NAME = new QName("http://www.arthan.org/", "TicketWebService");
        final Service service = Service.create(wsldURL, SERVICE_NAME);
        final TicketWebService infoService = service.getPort(TicketWebService.class);

        logger.info("Got the service: " + infoService);

        infoService.bookSeat(seatID);
        logger.info("Ticket booked with JAX-WS Service");

        final List<SeatDto> seats = infoService.getSeats();

        dumpSeatsList(seats);
    }

    private static void dumpSeatsList(List<SeatDto> seats) {
        seats.stream()
                .forEach(seat -> logger.info(seat.toString()));
    }
}

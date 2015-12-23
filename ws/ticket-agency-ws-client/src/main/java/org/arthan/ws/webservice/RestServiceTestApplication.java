package org.arthan.ws.webservice;

import org.arthan.ws.boundary.AccountDto;
import org.arthan.ws.boundary.SeatDto;

import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created by Артур on 23.12.2015.
 * Next to Ufa.
 */
public class RestServiceTestApplication {

    private final static String APPLICATION_URL = "http://localhost:8080/ticket-agency-ws/rest/";

    private WebTarget accountResource;
    private WebTarget seatResource;

    public static void main(String[] args) {
        new RestServiceTestApplication().runSample();
    }

    public RestServiceTestApplication() {
        final Client restClient = ClientBuilder.newClient();

        accountResource = restClient.target(APPLICATION_URL + "account");
        seatResource = restClient.target(APPLICATION_URL + "seat");
    }

    private void runSample() {
        printAccountStatusFromServer();

        System.out.println("=== Current status: ");
        Collection<SeatDto> seats = getSeatsFromServer();
        printSeats(seats);

        System.out.println("--- Booking: ");
        bookSeats(seats);

        System.out.println("=== Status after booking: ");
        Collection<SeatDto> bookedSeats = getSeatsFromServer();
        printSeats(bookedSeats);

        printAccountStatusFromServer();
    }

    private void bookSeats(Collection<SeatDto> seats) {
        for (SeatDto seat : seats) {
            try {
                String idOfSeat = Integer.toString(seat.getId());
                seatResource.path(idOfSeat).request().post(Entity.json(""), String.class);
                System.out.println(seat + "booked");
            } catch (WebApplicationException e) {
                Response response = e.getResponse();
                Response.StatusType statusInfo = response.getStatusInfo();
                System.out.println(seat + " not booked (" + statusInfo.getReasonPhrase() + "):" +
                        response.readEntity(JsonObject.class).getString("entity"));
            }
        }
    }

    private void printSeats(Collection<SeatDto> seats) {
        seats.stream().forEach(System.out::println);
    }

    private void printAccountStatusFromServer() {
        AccountDto accountDto = accountResource.request().get(AccountDto.class);
        System.out.println(accountDto);
    }

    public Collection<SeatDto> getSeatsFromServer() {
        return seatResource.request().get(new GenericType<Collection<SeatDto>>() {});
    }
}

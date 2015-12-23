package org.arthan.ws.boundary;

import org.arthan.ws.controller.TheatreBooker;
import org.arthan.ws.controller.TheatreBox;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Артур on 23.12.2015.
 * Next to Ufa.
 */

@Path("/seat")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class SeatsResource {

    @Inject
    TheatreBooker theatreBooker;

    @Inject
    TheatreBox theatreBox;

    @GET
    public Collection<SeatDto> getSeatList() {
        return theatreBox.getSeats()
                .stream()
                .map(SeatDto::fromSeat)
                .collect(Collectors.toList());
    }

    @POST
    @Path("/{id}")
    public Response bookPlace(@PathParam("id") int seatID) {
        try {
            theatreBooker.bookSeat(seatID);
            return Response.ok(SeatDto.fromSeat(theatreBox.getSeat(seatID)))
                    .build();
        } catch (Exception e) {
            final Entity<String> errorMessage = Entity.json(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorMessage)
                    .build();
        }
    }
}

package org.arthan.ws.boundary;

import org.arthan.ws.controller.TheatreBooker;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Артур on 23.12.2015.
 * Next to Ufa.
 */

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AccountResource {

    @Inject
    TheatreBooker theatreBooker;

    @GET
    public AccountDto getAccount() {
        return AccountDto.fromAccount(theatreBooker.getCurrentAccount());
    }

    @POST
    public Response renew() {
        theatreBooker.createCustomer();
        return Response
                .ok(AccountDto.fromAccount(theatreBooker.getCurrentAccount()))
                .build();
    }
}

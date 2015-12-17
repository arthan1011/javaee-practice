package org.arthan.jms.controller;

import org.arthan.jms.control.TicketService;
import org.arthan.jms.entity.SeatPosition;
import org.arthan.jms.entity.SeatType;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ashamsiev on 16.12.2015
 */

@Model
public class TheatreSetupService {

    @Inject
    private FacesContext facesContext;
    @Inject
    private TicketService ticketService;
    @Inject
    private List<SeatType> seatTypes;

    @Produces
    @Named
    private SeatType newSeatType;

    @PostConstruct
    public void initNewSeatType() {
        newSeatType = new SeatType();
    }

    public String createTheatre() {
        ticketService.createTheatre(seatTypes);
        return "book";
    }

    public String restart() {
        ticketService.doCleanUp();
        return "/index";
    }

    public void addNewSeats() throws Exception {
        try {
            ticketService.createSeatType(newSeatType);
            FacesMessage addedMessage = new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    "Done!",
                    "Seats Added");
            facesContext.addMessage(null, addedMessage);
            initNewSeatType();
        } catch (Exception e) {
            final String errorMessage = getRootErrorMessage(e);
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    errorMessage,
                    "Error while saving data");
            facesContext.addMessage(null, facesMessage);
        }
    }

    private String getRootErrorMessage(Exception exception) {
        String errorMessage = "Registration failed. See server log for more information";
        if (exception == null) {
            return errorMessage;
        }
        Throwable t = exception;
        while (t != null) {
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }

        return errorMessage;
    }

    public List<SeatPosition> getSeatPositions() {
        return Arrays.asList(SeatPosition.values());
    }
}
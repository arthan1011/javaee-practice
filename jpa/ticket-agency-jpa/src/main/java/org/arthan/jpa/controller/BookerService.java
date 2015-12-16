package org.arthan.jpa.controller;

import org.arthan.jpa.control.TicketService;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by ashamsiev on 16.12.2015
 */

@Named
@ViewScoped
public class BookerService implements Serializable {

    @Inject
    Logger logger;
    @Inject
    TicketService ticketService;
    @Inject
    FacesContext facesContext;

    private int money;

    @PostConstruct
    public void createCustomer() {
        money = 100;
    }

    public void bookSeat(long seatID, int price) {
        logger.info("Booking seat #" + seatID);
        if (price > money) {
            FacesMessage notMoneyMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Not enough money!",
                    "Registration unsuccessful");
            facesContext.addMessage(null, notMoneyMessage);
            return;
        }

        ticketService.bookSeat(seatID);

        FacesMessage bookedMessage = new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                "Registered!",
                "Registration successful");
        facesContext.addMessage(null, bookedMessage);
        logger.infov("Seat #{0} booked.", seatID);

        money -= price;
    }

    public int getMoney() {
        return money;
    }
}


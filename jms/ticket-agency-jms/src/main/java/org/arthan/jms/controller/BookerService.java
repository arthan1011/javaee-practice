package org.arthan.jms.controller;

import org.arthan.jms.control.TicketService;
import org.arthan.jms.jms.BookingQueueProducer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.logging.Logger;

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
    BookingQueueProducer bookingQueueProducer;

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
            FacesContext.getCurrentInstance().addMessage(null, notMoneyMessage);
            return;
        }

        ticketService.bookSeat(seatID);

        FacesMessage bookedMessage = new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                "Registered!",
                "Registration successful");
        FacesContext.getCurrentInstance().addMessage(null, bookedMessage);
        logger.info(MessageFormat.format("Seat #{0} booked.", seatID));

        money -= price;

        bookingQueueProducer.sendMessage("[JMS Message] User registered seat " + seatID);
    }

    public int getMoney() {
        return money;
    }
}


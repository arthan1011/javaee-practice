package org.arthan.ws.controller;

import org.arthan.ws.annotations.Logged;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by ashamsiev on 15.12.2015
 */

@SessionScoped
@Named
@Logged
public class TheatreBooker implements Serializable {

    @Inject
    Logger logger;
    @Inject
    TheatreBox theatreBox;
    @Inject
    FacesContext facesContext;

    private int money;

    @PostConstruct
    public void createCustomer() {
        money = 100;
    }

    public void bookSeat(int seatID) {
        logger.info("Booking seat #" + seatID);
        int price = theatreBox.getSeatPrice(seatID);

        if (price > money) {
            FacesMessage noMoneyMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Not enough money!", "Registration unsuccessful");
            facesContext.addMessage(null, noMoneyMessage);
            return;
        }

        theatreBox.buyTicket(seatID);

        FacesMessage successMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Booked!", "Booking successful");
        facesContext.addMessage(null, successMessage);

        logger.info("Seat #" + seatID + " booked.");

        money -= price;
    }

    public int getMoney() {
        return money;
    }
}

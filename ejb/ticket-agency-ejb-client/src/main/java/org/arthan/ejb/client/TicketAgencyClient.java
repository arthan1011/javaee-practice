package org.arthan.ejb.client;

import org.arthan.ejb.boundary.TheatreBookerRemote;
import org.arthan.ejb.boundary.TheatreInfoRemote;
import org.arthan.ejb.exceptions.NoSuchSeatException;
import org.arthan.ejb.exceptions.NotEnoughMoneyException;
import org.arthan.ejb.exceptions.SeatBookedException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ashamsiev on 14.12.2015
 */
public class TicketAgencyClient {

    private final Logger logger = Logger.getLogger(TicketAgencyClient.class.getName());

    public static void main(String[] args) throws Exception {
        Logger.getLogger("org.jboss").setLevel(Level.SEVERE);
        Logger.getLogger("org.xnio").setLevel(Level.SEVERE);

        new TicketAgencyClient().run();
    }

    private final Context context;
    private TheatreInfoRemote theatreInfo;
    private TheatreBookerRemote theatreBooker;

    public TicketAgencyClient() throws NamingException {
        final Properties jndiProperties = new Properties();
        jndiProperties.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//        jndiProperties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        this.context = new InitialContext(jndiProperties);
    }

    private enum Command {
        BOOK, LIST, MONEY, QUIT, INVALID;

        public static Command parseCommand(String command) {
            try {
                return valueOf(command.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                return INVALID;
            }
        }
    }

    private void run() throws NamingException {
        this.theatreInfo = lookupTheatreInfoEJB();
        this.theatreBooker = lookupTheatreBookerEJB();

        showWelcomeMessage();

        while (true) {
            final String stringCommand = IOUtils.readLine("> ");
            Command command = Command.parseCommand(stringCommand);

            switch (command) {
                case BOOK:
                    handleBook();
                    break;
                case LIST:
                    handleList();
                    break;
                case MONEY:
                    handleMoney();
                    break;
                case QUIT:
                    handleQuit();
                    break;
                default:
                    logger.warning("Unknown command " + stringCommand);
            }
        }
    }

    private void showWelcomeMessage() {
        System.out.println("Theatre booking system");
        System.out.println("+++++++++++++++++++++++++++++++++");
        System.out.println("Commands; book, list, money, quit");
    }

    private TheatreBookerRemote lookupTheatreBookerEJB() throws NamingException {
        return (TheatreBookerRemote) context.lookup("ejb:/ticket-agency-ejb/TheatreBooker!org.arthan.ejb.boundary.TheatreBookerRemote?stateful");
    }

    private TheatreInfoRemote lookupTheatreInfoEJB() throws NamingException {
        return (TheatreInfoRemote) context.lookup("ejb:/ticket-agency-ejb/TheatreInfo!org.arthan.ejb.boundary.TheatreInfoRemote");
    }

    private void handleQuit() {
        logger.info("Bye");
        System.exit(0);
    }

    private void handleMoney() {
        final int accountBalance = theatreBooker.getAccountBalance();
        logger.info("You have: " + accountBalance + " money left.");
    }

    private void handleList() {
        logger.info(theatreInfo.printSeatList());
    }

    private void handleBook() {
        int seatID;

        try {
            seatID = IOUtils.readInt("Enter seat ID: ");
        } catch (NumberFormatException e) {
            logger.warning("Wrong Seat ID format");
            return;
        }

        try {
            String retVal = theatreBooker.bookSeat(seatID);
            System.out.println(retVal);
        } catch (NoSuchSeatException | SeatBookedException | NotEnoughMoneyException e) {
            logger.warning(e.getMessage());
        }
    }


}

package org.arthan.jms.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Logger;

/**
 * Created by ashamsiev on 17.12.2015
 */

@MessageDriven(name = "BookingQueueReceiver", activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destinationLookup",
                propertyValue = BookingQueueDefinition.BOOKING_QUEUE
        ),
        @ActivationConfigProperty(
                propertyName = "destinationType",
                propertyValue = "javax.jsm.Queue"
        )
})
public class BookingQueueReceiver implements MessageListener {

    @Inject
    Logger logger;

    public void onMessage(Message message) {
        try {
            final String text = message.getBody(String.class);
            logger.info("Received message: " + text);
        } catch (JMSException e) {
            logger.severe(e.toString());
        }
    }
}

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

@MessageDriven(name = "LowPriorityBookingQueueReceiver", activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destinationLookup",
                propertyValue = BookingQueueDefinition.BOOKING_QUEUE
        ),
        @ActivationConfigProperty(
                propertyName = "destinationType",
                propertyValue = "javax.jms.Queue"
        ),
        @ActivationConfigProperty(
                propertyName = "messageSelector",
                propertyValue = "priority = 'LOW'"
        )
})
public class LowPriorityBookingQueueReceiver implements MessageListener {

    @Inject
    Logger logger;

    public void onMessage(Message message) {
        try {
            final String text = message.getBody(String.class);
            logger.info("Low priority message received: " + text);
        } catch (JMSException e) {
            logger.severe(e.toString());
        }
    }
}

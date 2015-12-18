package org.arthan.jms.jms;

import org.jboss.ejb3.annotation.ResourceAdapter;

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

@MessageDriven(name = "MDBService", activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destination",
                propertyValue = "java:jboss/activemq/queue/TicketQueue"
        ),
        @ActivationConfigProperty(
                propertyName = "destinationType",
                propertyValue = "javax.jms.Queue"
        )
})
@ResourceAdapter(value = "activemq-rar-5.9.0.rar")
public class MDBService implements MessageListener {

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

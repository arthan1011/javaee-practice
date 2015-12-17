package org.arthan.jms.jms;

import javax.jms.JMSDestinationDefinition;

/**
 * Created by ashamsiev on 17.12.2015
 */

@JMSDestinationDefinition(
        name = BookingQueueDefinition.BOOKING_QUEUE,
        interfaceName = "javax.jms.Queue"
)
public class BookingQueueDefinition {
    public static final String BOOKING_QUEUE = "java:global/jms/bookingQueue";
}

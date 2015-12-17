package org.arthan.jms.jms;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 * Created by ashamsiev on 17.12.2015
 */

@ApplicationScoped
public class BookingQueueProducer {

    @Inject
    BookingCompletionListener bookingCompletionListener;
    @Inject
    JMSContext context;
    @Resource(mappedName = BookingQueueDefinition.BOOKING_QUEUE)
    Queue syncQueue;

    public void sendMessage(String text, Priority priority) {
        context.createProducer()
                .setProperty("priority", priority.toString())
                .setAsync(bookingCompletionListener)
                .send(syncQueue, text);
    }
}

package org.arthan.ticketagency.test;

import org.arthan.test.control.SeatTypeDao;
import org.arthan.test.control.TicketService;
import org.arthan.test.entity.SeatType;
import org.arthan.test.util.LoggerProducer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.PersistenceTest;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ashamsiev on 12.01.2016
 */

@RunWith(Arquillian.class)
public class TicketAgencyTest {

    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackage(SeatType.class.getPackage())
                .addPackage(TicketService.class.getPackage())
                .addPackage(LoggerProducer.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    TicketService ticketService;
    @Inject
    SeatTypeDao seatTypeDao;

    @Test
    @UsingDataSet("datasets/seats.yml")
    @ShouldMatchDataSet("datasets/expected-seats.yml")
    public void shouldMakeACleanUp() throws Exception {
        ticketService.doCleanUp();
        List<SeatType> all = seatTypeDao.findAll();
        System.out.println("SIZE: " + all.size());
    }

    @Test
    public void shouldCreateSeatType() throws Exception {
        final SeatType seatType = new SeatType();
        seatType.setDescription("Balcony Test");
        seatType.setPrice(12);
        seatType.setQuantity(6);

        ticketService.createSeatType(seatType);

        assertNotNull(seatType.getId());
    }
}

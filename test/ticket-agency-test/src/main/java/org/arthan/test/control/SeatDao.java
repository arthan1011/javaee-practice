package org.arthan.test.control;

import org.arthan.test.entity.Seat;

import javax.ejb.Stateless;

/**
 * Created by ashamsiev on 16.12.2015
 */

@Stateless
public class SeatDao extends AbstractDao<Seat> {
    public SeatDao() {
        super(Seat.class);
    }
}

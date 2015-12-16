package org.arthan.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ashamsiev on 16.12.2015
 */

@Entity
@Table(name = "seat")
public class Seat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean booked;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private SeatType type;
}

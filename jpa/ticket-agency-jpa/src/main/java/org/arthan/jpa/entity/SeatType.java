package org.arthan.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ashamsiev on 16.12.2015
 */

@Entity
@Table(name = "seat_type")
public class SeatType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private int price;

    private int quantity;

    @OneToMany(mappedBy = "seatType", fetch = FetchType.EAGER)
    private List<Seat> seats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}

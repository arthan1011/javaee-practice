package org.arthan.jpa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @NotNull
    @Size(min = 1, max = 25, message = "You need to enter a seat description (max 25 char)")
    @Pattern(regexp = "[A-Za-z\\s]*", message = "Description must contain only letters and spaces")
    private String description;

    @NotNull
    private int price;

    @NotNull
    private int quantity;

    private SeatPosition position;

    @OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
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

    public SeatPosition getPosition() {
        return position;
    }

    public void setPosition(SeatPosition position) {
        this.position = position;
    }
}

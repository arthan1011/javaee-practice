package org.arthan.ejb.model;

/**
 * Created by ashamsiev on 11.12.2015
 */
public class Seat {
    private int id;
    private String name;
    private int price;
    private boolean booked;

    public Seat(int id, String name, int price, boolean booked) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.booked = booked;
    }

    public Seat(int id, String name, int price) {
        this(id, name, price, false);
    }

    public Seat getBookedSeat() {
        return new Seat(getId(), getName(), getPrice(), true);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}

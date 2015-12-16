package org.arthan.jpa.entity;

/**
 * Created by ashamsiev on 16.12.2015
 */
public enum SeatPosition {

    ORCHESTRA("Orchestra", "orchestra"),
    BOX("Box", "box"),
    BALCONY("Balcony", "balcony");

    private final String label;
    private final String dbRepresentation;

    SeatPosition(String label, String dbRepresentation) {
        this.label = label;
        this.dbRepresentation = dbRepresentation;
    }

    public String getLabel() {
        return label;
    }

    public String getDbRepresentation() {
        return dbRepresentation;
    }
}

package org.arthan.jpa.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by ashamsiev on 16.12.2015
 */

@Converter(autoApply = true)
public class SeatPositionConverter implements AttributeConverter<SeatPosition, String> {
    @Override
    public String convertToDatabaseColumn(SeatPosition attribute) {
        return attribute.getDbRepresentation();
    }

    @Override
    public SeatPosition convertToEntityAttribute(String dbData) {
        for (SeatPosition seatPosition : SeatPosition.values()) {
            if (seatPosition.getDbRepresentation().equals(dbData)) {
                return seatPosition;
            }
        }
        throw new IllegalArgumentException("Unknown attribute value: " + dbData);
    }
}

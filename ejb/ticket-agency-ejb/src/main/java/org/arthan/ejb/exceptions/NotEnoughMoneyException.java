package org.arthan.ejb.exceptions;

/**
 * Created by ashamsiev on 14.12.2015
 */
public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}

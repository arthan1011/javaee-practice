package org.arthan.ws.entity;

/**
 * Created by Артур on 23.12.2015.
 * Next to Ufa.
 */
public class Account {
    private final int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public Account charge(int amount) {
        final int newBalance = balance - amount;
        if (newBalance< 0) {
            throw new IllegalArgumentException("Debit value on account");
        }
        return new Account(newBalance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }

    public int getBalance() {
        return balance;
    }
}

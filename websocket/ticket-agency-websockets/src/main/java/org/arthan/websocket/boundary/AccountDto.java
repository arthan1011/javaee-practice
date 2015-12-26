package org.arthan.websocket.boundary;

import org.arthan.websocket.entity.Account;

/**
 * Created by Артур on 23.12.2015.
 * Next to Ufa.
 */
public class AccountDto {

    private int balance;

    public AccountDto(int balance) {
        this.balance = balance;
    }

    public AccountDto() {
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "balance=" + balance +
                '}';
    }

    public static AccountDto fromAccount(Account account) {
        return new AccountDto(account.getBalance());
    }
}

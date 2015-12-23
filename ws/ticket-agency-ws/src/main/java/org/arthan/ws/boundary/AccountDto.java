package org.arthan.ws.boundary;

import org.arthan.ws.entity.Account;

/**
 * Created by ����� on 23.12.2015.
 * Next to Ufa.
 */
public class AccountDto {

    private int balance;

    public AccountDto(int balance) {
        this.balance = balance;
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

package org.example;

import java.math.BigDecimal;

public class AccountDemo {
    public static void main(String[] args) {
        Account account1 = new Account("Visal", new BigDecimal("300"));
        Account account2 = new Account("John", new BigDecimal("10"));

        account2.deposit(new BigDecimal("50"));

        System.out.printf("%s: %s%n", account1.getOwner(), account1.getBalance());
        System.out.printf("%s: %s%n", account2.getOwner(), account2.getBalance());

        account1.withdraw(new BigDecimal("100"));
        System.out.printf("%s: %s%n", account1.getOwner(), account1.getBalance());

        account1.transfer(account1, new BigDecimal("50"));

        System.out.printf("%s: %s%n", account1.getOwner(), account1.getBalance());
        System.out.printf("%s: %s%n", account2.getOwner(), account2.getBalance());
    }
}

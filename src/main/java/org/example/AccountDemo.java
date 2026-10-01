package org.example;

public class AccountDemo {
    public static void main(String[] args) {
        Account account1 = new Account();
        Account account2 = new Account();

        account1.owner = "Visal";
        account1.balance = 300;

        account2.owner = "John";
        account2.balance = 10;
        account2.deposit(50);

        System.out.printf("%s: %d%n", account1.owner, account1.balance);
        System.out.printf("%s: %d%n", account2.owner, account2.balance);
    }
}

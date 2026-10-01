package org.example;

public class AccountDemo {
    public static void main(String[] args) {
        Account account1 = new Account("Visal", 300);
        Account account2 = new Account("John", 10);

        account2.deposit(50);

        System.out.printf("%s: %d%n", account1.getOwner(), account1.getBalance());
        System.out.printf("%s: %d%n", account2.getOwner(), account2.getBalance());

        account1.withdraw(100);
        System.out.printf("%s: %d%n", account1.getOwner(), account1.getBalance());

        account1.withdraw(-50);
        account1.withdraw(1000);
    }
}

package org.example;

public class Account {
    private String owner;
    private int balance;

    public Account(String owner, int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Amount can't be less than 0");
        }
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public int getBalance() {
        return balance;
    }

    void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount can't be less than or equal to 0");
        }
        balance += amount;
    }

    void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount can't be less than or equal to 0");
        }
        int remaining = balance - amount;
        if (remaining < 0) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance = remaining;
    }
}

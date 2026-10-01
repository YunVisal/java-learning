package org.example;

public class Account {
    String owner;
    int balance;

    void deposit(int amount) {
        balance += amount;
    }
}

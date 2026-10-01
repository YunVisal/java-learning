package org.example;

public class Account {
    private String owner;
    private int balance;

    public Account(String owner, int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance can't be less than 0.");
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

    public void deposit(int amount) {
        validateAmount(amount);
        balance += amount;
    }

    public void withdraw(int amount) {
        validateAmount(amount);
        int remaining = balance - amount;
        if (remaining < 0) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance = remaining;
    }

    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount can't be less than or equal to 0.");
        }
    }

    public void transfer(Account to, int amount) {
        if (to == this) {
            throw new IllegalArgumentException("Sender and receiver account can't be the same.");
        }
        withdraw(amount);
        to.deposit(amount);
    }
}

package org.example;

import java.math.BigDecimal;

public class Account {
    private String owner;
    private BigDecimal balance;

    public Account(String owner, BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Balance can't be less than 0.");
        }

        validateDecimalPlaces(balance, "Balance");
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        validateAmount(amount);
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        validateAmount(amount);
        BigDecimal remaining = balance.subtract(amount);
        if (remaining.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance = remaining;
    }

    private void validateAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount can't be less than or equal to 0.");
        }

        validateDecimalPlaces(amount, "Amount");
    }

    public void transfer(Account to, BigDecimal amount) {
        if (to == this) {
            throw new IllegalArgumentException("Sender and receiver account can't be the same.");
        }
        withdraw(amount);
        to.deposit(amount);
    }

    private void validateDecimalPlaces(BigDecimal amount, String label) {
        if (amount.scale() > 2) {
            throw new IllegalArgumentException(String.format("%s should not have more than 2 decimal places.", label));
        }
    }
}

package org.example;

import java.util.Scanner;

public class Balance {
    public static void main(String[] args) {
        int totalIncome = 0;
        int totalExpense = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            int amount = readAmount(scanner);
            if (amount > 0) {
                totalIncome += amount;
            } else if (amount < 0) {
                totalExpense += Math.abs(amount);
            } else {
                break;
            }
        }

        System.out.printf("Total income: %d.%n", totalIncome);
        System.out.printf("Total expense: %d.%n", totalExpense);
        System.out.printf("Balance: %d.%n", totalIncome - totalExpense);
    }

    static int readAmount(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter the amount (positive number for income, negative number for expense, 0 to stop): ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input! Please enter a whole number.");
            }
        }
    }
}

package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseInput {
    public static void main(String[] args) {
        ArrayList<Integer> expenses = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            int expense = readExpense(scanner);
            if (expense == 0) {
                break;
            }

            expenses.add(expense);
        }

        System.out.printf("Number of expenses: %d.%n", expenses.size());

        int totalExpenses = 0;
        for (int expense : expenses) {
            totalExpenses += expense;
        }
        System.out.printf("Total expenses: %d.%n", totalExpenses);

        if (expenses.isEmpty()) {
            System.out.println("No largest expense available because no expense was entered.");
        } else {
            int largestExpense = expenses.getFirst();
            for (int i = 1; i < expenses.size(); i++) {
                if (largestExpense < expenses.get(i)) {
                    largestExpense = expenses.get(i);
                }
            }
            System.out.printf("Largest expense: %d.%n", largestExpense);
        }
    }

    static int readExpense(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter the expense (0 to stop): ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input! Please enter a whole number.");
            }
        }
    }
}

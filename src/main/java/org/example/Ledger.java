package org.example;

import java.util.ArrayList;

public class Ledger {
    public static void main(String[] args) {
        ArrayList<Integer> expenses = new ArrayList<>();

        expenses.add(1);
        expenses.add(2);
        expenses.add(12);
        expenses.add(3);
        expenses.add(6);

        System.out.printf("Number of expenses: %d.%n", expenses.size());
        System.out.printf("First expense: %d.%n", expenses.get(0));
        System.out.printf("Last expense: %d.%n", expenses.get(expenses.size() - 1));

        int totalExpenses = 0;
        for (int i = 0; i < expenses.size(); i++) {
            totalExpenses += expenses.get(i);
        }
        System.out.printf("Total expenses: %d.%n", totalExpenses);

        int totalExpensesWithForEach = 0;
        for (int expense : expenses) {
            totalExpensesWithForEach += expense;
        }
        System.out.printf("Total expenses with ForEach: %d.%n", totalExpensesWithForEach);

        expenses.remove(1);
        System.out.printf("Number of expenses: %d.%n", expenses.size());
        for (int i = 0; i < expenses.size(); i++) {
            System.out.printf("#%d: %d.%n", i, expenses.get(i));
        }
    }
}

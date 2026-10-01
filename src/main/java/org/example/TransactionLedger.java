package org.example;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TransactionLedger {
    static final String EXIT_WORD = "done";

    static final String INCOME_TYPE_WORD = "income";
    static final String EXPENSE_TYPE_WORD = "expense";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> totals = new TreeMap<>();
        int totalIncome = 0;
        int totalExpenses = 0;

        while (true) {
            String transactionType = readType(scanner);
            if (transactionType.equalsIgnoreCase(EXIT_WORD)) {
                break;
            }
            String category = readCategory(scanner);
            int amount = readAmount(scanner);
            addToTotal(totals, category.trim().toLowerCase(), amount);

            if (transactionType.equalsIgnoreCase(INCOME_TYPE_WORD)) {
                totalIncome += amount;
            } else if (transactionType.equalsIgnoreCase(EXPENSE_TYPE_WORD)) {
                totalExpenses += amount;
            }
        }

        for (Map.Entry<String, Integer> total : totals.entrySet()) {
            System.out.printf("%s: %d%n", total.getKey(), total.getValue());
        }

        System.out.printf("Total income: %d%n", totalIncome);
        System.out.printf("Total expenses: %d%n", totalExpenses);
        System.out.printf("Balance: %d%n", totalIncome - totalExpenses);
        System.out.println("Goodbye!");
    }

    public static String readCategory(Scanner scanner) {
        while (true) {
            System.out.print("Category: ");
            String input = scanner.nextLine();
            if (!input.isBlank()) {
                return input;
            }
            System.out.println("Category can't be empty, try again.");
        }
    }

    public static int readAmount(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Amount: ");
                int input = Integer.parseInt(scanner.nextLine());
                if (input > 0) {
                    return input;
                }
                System.out.println("Amount can't be zero or negative, try again.");
            } catch (NumberFormatException e) {
                System.out.println("Not a number, try again.");
            }
        }
    }

    public static void addToTotal(Map<String, Integer> totals, String category, int amount) {
        totals.put(category, totals.getOrDefault(category, 0) + amount);
    }

    public static String readType(Scanner scanner) {
        while (true) {
            System.out.printf("Type (%s/%s or '%s' to exit): ", INCOME_TYPE_WORD, EXPENSE_TYPE_WORD, EXIT_WORD);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase(INCOME_TYPE_WORD) || input.equalsIgnoreCase(EXPENSE_TYPE_WORD) || input.equalsIgnoreCase(EXIT_WORD)) {
                return input;
            }
            System.out.printf("Invalid input. Please input any of '%s', '%s' or '%s'.%n", INCOME_TYPE_WORD, EXPENSE_TYPE_WORD, EXIT_WORD);
        }
    }
}

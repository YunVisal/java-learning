package org.example;

import java.util.Scanner;

public class TipCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your bill amount: ");
        double billAmount = Double.parseDouble(scanner.nextLine());
        if (billAmount < 0) {
            System.out.println("Bill amount cannot be negative.");
            return;
        }

        int tipPercentage = readInt(scanner, "Enter your tip percentage: ", 0, "Tip percentage cannot be negative.");

        int people = readInt(scanner, "Enter number of people: ", 1, "Number of people must be at least 1.");

        double tipAmount = (tipPercentage / 100.0) * billAmount;
        double totalAmount = billAmount + tipAmount;
        double splitAmount = totalAmount / people;

        System.out.printf("Tip: $%.2f%n", tipAmount);
        System.out.printf("Total: $%.2f%n", totalAmount);
        System.out.printf("Individual split: $%.2f%n", splitAmount);
    }

    static int readInt(Scanner scanner, String prompt, int minimumAllowedValue, String validationMessage) {
        while (true) {
            System.out.print(prompt);
            int value = Integer.parseInt(scanner.nextLine());
            if (value >= minimumAllowedValue) {
                return value;
            }
            System.out.println(validationMessage);
        }
    }
}

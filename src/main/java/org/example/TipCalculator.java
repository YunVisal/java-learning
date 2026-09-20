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

        System.out.print("Enter your tip percentage: ");
        int tipPercentage = Integer.parseInt(scanner.nextLine());
        if (tipPercentage < 0) {
            System.out.println("Tip percentage cannot be negative.");
            return;
        }

        System.out.print("Enter number of people: ");
        int people = Integer.parseInt(scanner.nextLine());
        if (people < 1) {
            System.out.println("Number of people must be at least 1.");
            return;
        }

        double tipAmount = (tipPercentage / 100.0) * billAmount;
        double totalAmount = billAmount + tipAmount;
        double splitAmount = totalAmount / people;

        System.out.printf("Tip: $%.2f%n", tipAmount);
        System.out.printf("Total: $%.2f%n", totalAmount);
        System.out.printf("Individual split: $%.2f%n", splitAmount);
    }
}

package org.example;

public class ArrayBasics {
    public static void main(String[] args) {
        int[] amounts = {1, 3, 12, 2, 50};
        System.out.printf("First amount: %d%n", amounts[0]);
        System.out.printf("Last amount: %d%n", amounts[amounts.length - 1]);

        int totalAmount = 0;
        for (int i = 0; i < amounts.length; i++) {
            totalAmount += amounts[i];
        }
        System.out.printf("Total spent: %d%n", totalAmount);

        int maxAmount = amounts[0];
        for (int i = 1; i < amounts.length; i++) {
            if (amounts[i] > maxAmount) {
                maxAmount = amounts[i];
            }
        }
        System.out.printf("Largest transaction: %d%n", maxAmount);
    }
}

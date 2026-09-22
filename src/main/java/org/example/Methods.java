package org.example;

public class Methods {
    public static void main(String[] args) {
        double billAmount = 60.0;
        int tipPercentage = 15;
        double tipAmount = calculateTipAmount(billAmount, tipPercentage);

        System.out.printf("Tip: $%.2f for a $%.2f bill at %d%%.%n", tipAmount, billAmount, tipPercentage);
    }

    static double calculateTipAmount(double billAmount, int tipPercentage) {
        return billAmount * (tipPercentage / 100.0);
    }
}

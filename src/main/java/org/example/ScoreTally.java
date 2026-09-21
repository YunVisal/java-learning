package org.example;

import java.util.Scanner;

public class ScoreTally {
    public static void main(String[] args) {
        int totalScore = 0;
        int numberOfInput = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input the score: ");
            int input = Integer.parseInt(scanner.nextLine());

            if (input == -1) {
                break;
            }

            totalScore += input;
            numberOfInput += 1;
        }

        if (numberOfInput > 0) {
            double averageScore = (double) totalScore / numberOfInput;
            System.out.printf("Number of scores: %d%n", numberOfInput);
            System.out.printf("Average score: %.1f%n", averageScore);
        } else {
            System.out.println("No score was entered, cannot computed!");
        }
    }
}

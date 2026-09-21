package org.example;

import java.util.Scanner;

public class AverageLoop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of scores you want to input: ");
        int numberOfScores = Integer.parseInt(scanner.nextLine());
        if (numberOfScores < 1) {
            System.out.println("Number of scores must be at least 1.");
            return;
        }

        int totalScore = 0;
        for (int i = 0; i < numberOfScores; i++) {
            System.out.print("Enter the score: ");
            int score = Integer.parseInt(scanner.nextLine());
            totalScore += score;
        }

        double averageScore = (double) totalScore / numberOfScores;
        System.out.printf("Average Score: %.1f%n", averageScore);
    }
}

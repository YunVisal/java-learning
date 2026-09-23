package org.example;

import java.util.Scanner;

public class ScoreTally {
    static final int MIN_ALLOWED_SCORE = 0;
    static final int MAX_ALLOWED_SCORE = 100;

    static final int QUIT_INPUT = -1;

    static final double AVERAGE_NOT_COMPUTED = -1.0;

    public static void main(String[] args) {
        double averageScore = readScoresAndComputeAverage();
        if (averageScore == AVERAGE_NOT_COMPUTED) {
            return;
        }
        System.out.printf("Average score: %.1f%n", averageScore);
    }

    static double readScoresAndComputeAverage() {
        int totalScore = 0;
        int numberOfInput = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input the score: ");
            int input = Integer.parseInt(scanner.nextLine());

            if (input == QUIT_INPUT) {
                break;
            }

            if (!isInputValid(input, MIN_ALLOWED_SCORE, MAX_ALLOWED_SCORE)) {
                System.out.printf("Score should be at least %d and cannot be greater than %d%n", MIN_ALLOWED_SCORE, MAX_ALLOWED_SCORE);
                continue;
            }

            totalScore += input;
            numberOfInput += 1;
        }

        if (numberOfInput > 0) {
            double averageScore = (double) totalScore / numberOfInput;
            System.out.printf("Number of scores: %d%n", numberOfInput);
            return averageScore;
        } else {
            System.out.printf("No score was entered, cannot compute the average. Enter at least one score, then %d to finish.%n", QUIT_INPUT);
            return AVERAGE_NOT_COMPUTED;
        }
    }

    static boolean isInputValid(int score, int lowerBound, int upperBound) {
        return score >= lowerBound && score <= upperBound;
    }
}

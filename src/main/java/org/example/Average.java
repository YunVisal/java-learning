package org.example;

public class Average {
    public static void main(String[] args) {
        int[] scores = {90, 85, 82};
        int totalScore = 0;

        for (int i = 0; i < scores.length; i++) {
            totalScore += scores[i];
        }

        double averageScore = (double) totalScore / scores.length;
        System.out.printf("Average Score: %.1f%n", averageScore);
    }
}

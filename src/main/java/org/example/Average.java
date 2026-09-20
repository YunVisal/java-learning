package org.example;

public class Average {
    public static void main(String[] args) {
        int score1 = 90;
        int score2 = 85;
        int score3 = 82;

        double averageScore = (double)(score1 + score2 + score3) / 3;
        System.out.printf("Average Score: %.1f%n", averageScore);
    }
}

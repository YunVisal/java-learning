package org.example;

import java.util.Scanner;

public class Grader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your score: ");
        int score = Integer.parseInt(scanner.nextLine());

        if (score > 100 || score < 0) {
            System.out.println("Score must be between 0 and 100.");
        } else if (score >= 90) {
            System.out.println("A");
        } else if (score >= 80) {
            System.out.println("B");
        } else if (score >= 70) {
            System.out.println("C");
        } else if (score >= 60) {
            System.out.println("D");
        } else {
            System.out.println("F");
        }
    }
}

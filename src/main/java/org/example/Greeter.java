package org.example;

import java.util.Scanner;

public class Greeter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("What is your name? ");
        String name = scanner.nextLine();

        System.out.print("How old are you? ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.printf("Hello %s, next year you will be %d.%n", name, age + 1);
    }
}

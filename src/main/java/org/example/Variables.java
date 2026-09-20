package org.example;

public class Variables {
    public static void main(String[] args) {
        int age = 23;
        double gpa = 3.5;
        String name = "Visal";
        boolean isGraduated = false;

        System.out.println("Student Detail:");
        System.out.printf("Name: %s%n", name);
        System.out.printf("Age: %d%n", age);
        System.out.printf("GPA: %.1f%n", gpa);
        System.out.printf("Is Graduated: %b%n", isGraduated);
    }
}

package org.example;

public class Countdown {
    public static void main(String[] args) {
        int timer = 5;
        while (timer >= 0) {
            System.out.println(timer);
            timer -= 1;
        }
        System.out.println("Liftoff!");
    }
}

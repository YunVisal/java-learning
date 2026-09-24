package org.example;

import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    static final int MIN_GAME_RANGE = 1;
    static final int MAX_GAME_RANGE = 100;

    static final String PLAY_AGAIN_INPUT = "y";
    static final String QUIT_INPUT = "n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            play(scanner);

            if (!wantsToPlayAgain(scanner)) {
                System.out.println("Bye bye!");
                break;
            }
        }
    }

    static void play(Scanner scanner) {
        int secretNumber = generateSecretNumber();

        int guessCount = 0;
        System.out.println("Guess the secret number!");
        while (true) {
            int guess = readGuessNumber(scanner);

            if (!isGuessNumberValid(guess)) {
                System.out.printf("The number should be at least %d and cannot be greater than %d.%n", MIN_GAME_RANGE, MAX_GAME_RANGE);
                continue;
            }

            guessCount += 1;
            if (guess < secretNumber) {
                System.out.println("Too low! Try again.");
            } else if (guess > secretNumber) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Correct.");
                System.out.printf("Number of attempts: %d%n", guessCount);
                break;
            }
        }
    }

    static boolean wantsToPlayAgain(Scanner scanner) {
        while (true) {
            System.out.printf("Play again? (%s/%s): ", PLAY_AGAIN_INPUT, QUIT_INPUT);
            String playAgainInput = scanner.nextLine();
            if (playAgainInput.equals(PLAY_AGAIN_INPUT)) {
                return true;
            } else if (playAgainInput.equals(QUIT_INPUT)) {
                return false;
            } else {
                System.out.printf("Invalid choice. Valid choices are %s or %s.%n", PLAY_AGAIN_INPUT, QUIT_INPUT);
            }
        }
    }

    static int generateSecretNumber() {
        Random random = new Random();
        return random.nextInt(MIN_GAME_RANGE, MAX_GAME_RANGE + 1);
    }

    static int readGuessNumber(Scanner scanner) {
        System.out.printf("Enter the guess number (Range: %d-%d): ", MIN_GAME_RANGE, MAX_GAME_RANGE);
        return Integer.parseInt(scanner.nextLine());
    }

    static boolean isGuessNumberValid(int number) {
        return number >= MIN_GAME_RANGE && number <= MAX_GAME_RANGE;
    }
}

package org.example;

import java.math.BigDecimal;
import java.util.Scanner;

public class BankApp {
    static final int QUIT_OPTION_CODE = 0;
    static final String OPERATION_CANCEL_CODE = "-1";

    public static void main(String[] args) {
        Account account1 = new Account("Visal", new BigDecimal("300"));
        Account account2 = new Account("John", new BigDecimal("20"));
        Account[] accounts = new Account[]{account1, account2};

        Scanner scanner = new Scanner(System.in);
        System.out.println("==========Bank App==========");
        while (true) {
            showMenu();
            int menuOption = readMenuOption(scanner);
            if (menuOption == QUIT_OPTION_CODE) {
                break;
            }
            switch (menuOption) {
                case 1:
                    deposit(scanner, accounts);
                    break;
                case 2:
                    withdraw(scanner, accounts);
                    break;
                case 3:
                    transfer(scanner, accounts);
                    break;
                case 4:
                    showBalances(accounts);
                    break;
                default:
                    System.out.println("Invalid option, please try again");
            }
            System.out.println("====================");
        }

        System.out.println("Goodbye.");
    }

    static void showMenu() {
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Show balances");
        System.out.printf("%d. Quit%n", QUIT_OPTION_CODE);
    }

    static int readInt(Scanner scanner, String promptMessage) {
        while (true) {
            try {
                System.out.print(promptMessage);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Not a number, please try again.");
            }
        }
    }

    static int readMenuOption(Scanner scanner) {
        return readInt(scanner, "Enter option: ");
    }

    static void showBalances(Account[] accounts) {
        for (Account account : accounts) {
            System.out.printf("%s: %s%n", account.getOwner(), account.getBalance());
        }
    }

    static int readAccountOption(Scanner scanner, Account[] accounts, String promptMessage) {
        while (true) {
            int accountOption = readInt(scanner, promptMessage);
            if (accountOption >= 1 && accountOption <= accounts.length) {
                return accountOption;
            }
            System.out.println("Account is invalid, please try again");
        }
    }

    static BigDecimal readAmount(Scanner scanner, String promptMessage) {
        while (true) {
            try {
                System.out.print(promptMessage);
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Not a number, please try again.");
            }
        }
    }

    static void deposit(Scanner scanner, Account[] accounts) {
        String promptMessage = String.format("Enter account (1 to %d): ", accounts.length);
        int accountOption = readAccountOption(scanner, accounts, promptMessage);
        Account targetAccount = accounts[accountOption - 1];

        while (true) {
            BigDecimal amount = readAmount(scanner, String.format("Enter amount (%s to cancel): ", OPERATION_CANCEL_CODE));
            if (checkCancelAndNotify(amount)) {
                break;
            }
            try {
                targetAccount.deposit(amount);
                System.out.println("Deposit complete.");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void withdraw(Scanner scanner, Account[] accounts) {
        String promptMessage = String.format("Enter account (1 to %d): ", accounts.length);
        int accountOption = readAccountOption(scanner, accounts, promptMessage);
        Account targetAccount = accounts[accountOption - 1];

        while (true) {
            BigDecimal amount = readAmount(scanner, String.format("Enter amount (%s to cancel): ", OPERATION_CANCEL_CODE));
            if (checkCancelAndNotify(amount)) {
                break;
            }
            try {
                targetAccount.withdraw(amount);
                System.out.println("Withdrawal complete.");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void transfer(Scanner scanner, Account[] accounts) {
        Account sourceAccount;
        Account targetAccount;
        while (true) {
            String sourceAccountPromptMessage = String.format("Enter source account (1 to %d): ", accounts.length);
            int sourceAccountOption = readAccountOption(scanner, accounts, sourceAccountPromptMessage);
            sourceAccount = accounts[sourceAccountOption - 1];

            String targetAccountPromptMessage = String.format("Enter target account (1 to %d): ", accounts.length);
            int targetAccountOption = readAccountOption(scanner, accounts, targetAccountPromptMessage);
            targetAccount = accounts[targetAccountOption - 1];

            if (sourceAccount != targetAccount) {
                break;
            }
            System.out.println("Source and target account can't be the same");
        }

        while (true) {
            try {
                BigDecimal amount = readAmount(scanner, String.format("Enter amount (%s to cancel): ", OPERATION_CANCEL_CODE));
                if (checkCancelAndNotify(amount)) {
                    break;
                }

                sourceAccount.transfer(targetAccount, amount);
                System.out.println("Transfer complete.");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static boolean isCancelOperation(BigDecimal input) {
        return input.compareTo(new BigDecimal(OPERATION_CANCEL_CODE)) == 0;
    }

    static boolean checkCancelAndNotify(BigDecimal input) {
        var isCancel = isCancelOperation(input);
        if (isCancel) {
            System.out.println("Operation cancelled");
        }
        return isCancel;
    }
}

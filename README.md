# java-learning

My Java practice repository. I'm a senior CS student graduating in November 2026, working toward becoming a Pi-shaped
engineer specializing in Java and Flutter. Everything here is written by hand as I learn — no copied solutions.

## The plan

| Weeks | Focus                                                     |
|-------|-----------------------------------------------------------|
| 0     | Setup — JDK, IntelliJ, Git, GitHub                        |
| 1     | Java basics — variables, types, I/O, `if`/`else`          |
| 2     | Loops and methods                                         |
| 3     | Arrays, `ArrayList`, `HashMap`                            |
| 4     | OOP — classes, objects, constructors, encapsulation       |
| 5     | Dart basics                                               |
| 6–9   | Flutter — widgets, state, navigation, local storage, APIs |

After graduation: Spring Boot, SQL and REST APIs, then a full-stack Flutter app on my own backend.

I keep a running learning log in [PROGRESS.md](PROGRESS.md).

## What's here

All source lives in `src/main/java/org/example/`.

**Week 0**

- `HelloWorld.java` — first program; prints my name and my goal.

**Week 1**

- `Variables.java` — the four core types (`int`, `double`, `String`, `boolean`) and
  `printf` formatting.
- `Greeter.java` — reads a name and age with `Scanner`, prints next year's age.
- `Average.java` — averages three scores; a worked example of the integer-division trap, where `(90 + 85 + 82) / 3`
  silently gives `85.0` instead of `85.7` without a cast.
- `Grader.java` — reads a score and prints a letter grade with an `if`/`else if` chain, rejecting anything outside
  0–100.
- `TipCalculator.java` — **Week 1 project.** Reads a bill, a tip percentage and a party size, then prints the tip, the
  total, and the per-person share. Validates each input with guard clauses before doing any arithmetic.

**Week 2**

- `Countdown.java` — counts down from 5 to 0 with a `while` loop, then prints `Liftoff!`.
- `AverageLoop.java` — an upgrade of Week 1's `Average.java`. It asks how many scores the user wants to enter, reads
  them with a `for` loop, and prints the average.
- `Methods.java` — first method of my own: `calculateTipAmount` takes a bill and a tip percentage and returns the tip.
- `ScoreTally.java` — reads scores until the user types `-1`, rejects any score outside 0–100, and prints the average.
  Uses a sentinel loop, named constants, and a `boolean` validation method.
- `TipCalculator.java` — refactored. A single `readInt` method now reads the tip percentage and the party size. On bad
  input it asks again instead of ending the program.
- `GuessingGame.java` — **Week 2 project.** A number-guessing game (see below).

**GuessingGame**

The program picks a secret number from 1 to 100. The player keeps guessing, and after each guess the game says "Too low"
or "Too high". When the player gets it right, the game shows how many attempts it took and asks whether to play again. A
guess outside 1–100 is rejected and doesn't count as an attempt, so a typo doesn't cost the player anything. I split the
code so that `play` runs one round and `main` runs the whole session. Each method has one responsibility (the Single
Responsibility Principle), which makes each part easier to read and change on its own. Next, I would like to add a best
score that is kept across rounds, so the player has something to beat.

**Week 3**

- `ArrayBasics.java` — prints the first, last, max amount and total amount of the amount list. Uses an array to store
  the list of amounts.
- `Average.java`(refactor) — combines each score into scores' array. By doing so, the multiple variables to store each
  score are removed and also the hard-coded magic number. It now divides by the actual length of the array, so the issue
  with the mismatch of the number of scores is removed.
- `Ledger.java` — prints the first, last, count and total amount of the expenses list. It removes the second expense as
  well. Uses an `ArrayList` to store the list of amounts, a for-each loop to iterate over the list.
- `ExpenseInput.java` — reads expenses until the user types `0`, then prints the count, the total and the largest
  expense. Bad input (`abc`, `12.50`) and negative amounts are rejected and the user is asked again. Uses `try`/`catch`
  for `NumberFormatException`, and `isEmpty()` to handle a ledger with no expenses.
- `Balance.java` — reads signed amounts (positive for income, negative for expense, `0` to stop) and prints total
  income, total expenses and the balance. Its weakness led to the project's design: a forgotten minus sign silently
  turns an expense into income.
- `CategoryTotals.java` — adds up expenses per category in a `TreeMap`, so the output is sorted by category name. Uses
  an `addExpense` method with `getOrDefault` so a category's first expense doesn't cause a `NullPointerException`.
- `TransactionLedger.java` — **Week 3 project.** A transaction ledger (see below).

**TransactionLedger**

The program records the income and expense of the user. It will ask for transaction's category and amount. When the user
finishes their recording by typing 'done', it will print out the list of each transaction's category and its total
amount along with the summarization of total expense, total income and remaining balance. To differentiate between the
income and expense record, I prompt the user to input the transaction type before asking them to input category and
amount. By doing this, I make sure the balance is not wrong by twice the amount in case the user forgets the minus sign
on expense record. As for the next improvement, I want to display the separate list of income and expense by the end of
the program.

**Week 4**

- `Account.java` — my first class. A bank account with a private owner and `BigDecimal` balance, and `deposit`,
  `withdraw` and `transfer` methods. The constructor and every method validate their input, so the balance can never go
  negative and amounts can't have more than 2 decimal places.
- `AccountDemo.java` — creates two accounts and calls each method. It ends with a transfer from an account to itself,
  which `Account` rejects with an `IllegalArgumentException`.
- `MoneyDemo.java` — shows why money uses `BigDecimal`: `0.1 + 0.2` gives exactly `0.3`, while `double` gives
  `0.30000000000000004`.
- `BankApp.java` — **Week 4 project.** A menu-driven bank app (see below).

**BankApp**

A menu-driven banking simulator. The user can deposit, withdraw, transfer money between two accounts, and view both
balances, until they choose to quit. Balances are stored as `BigDecimal` instead of `double`, because `double` can't
represent amounts like `0.1` exactly and small rounding errors add up. The money rules (no negative balance, no
amount of zero or less, at most 2 decimal places) live inside the `Account` class, not in the menu code. That way every
caller is protected, not just `BankApp`: a direct `account.deposit(new BigDecimal("0.001"))` is rejected even without
any prompt. `BankApp` catches the exceptions `Account` throws and shows the message, so bad input never crashes the app,
and the user can type `-1` to cancel an operation. Next, I would like to save the accounts to a database, so balances
survive after the program ends.

## Running it

Maven project, Java 25. Open in IntelliJ IDEA and run any class's `main` directly, or from the command line:

```bash
mvn compile
java -cp target/classes org.example.TipCalculator
```

## Known limitations

Deliberate, and noted so I remember to come back to them:

- Non-numeric input still crashes some Week 1–2 programs. The Week 3 programs catch `NumberFormatException` and ask
  again.
- Weeks 1–3 store money in `double`. Fine at this scale, wrong for real currency; from Week 4 on, `Account` uses
  `BigDecimal`.
- `TransactionLedger` keeps only one total per category; the individual transactions are not stored, so they can't be
  listed or corrected afterwards.
- `BankApp` prints balances without a fixed 2 decimals, so `25.50` shows as `25.5`.
- `BankApp` has only two hard-coded accounts, identified by owner name; there are no account numbers, so two accounts
  with the same owner can't be told apart.
- `BankApp` keeps everything in memory; all balances are lost when the program ends.

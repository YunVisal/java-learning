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

The program picks a secret number from 1 to 100. The player keeps guessing, and after each guess the game says "Too
low" or "Too high". When the player gets it right, the game shows how many attempts it took and asks whether to play
again. A guess outside 1–100 is rejected and doesn't count as an attempt, so a typo doesn't cost the player anything.
I split the code so that `play` runs one round and `main` runs the whole session. Each method has one responsibility
(the Single Responsibility Principle), which makes each part easier to read and change on its own. Next, I would like
to add a best score that is kept across rounds, so the player has something to beat.

## Running it

Maven project, Java 25. Open in IntelliJ IDEA and run any class's `main` directly, or from the command line:

```bash
mvn compile
java -cp target/classes org.example.TipCalculator
```

## Known limitations

Deliberate, and noted so I remember to come back to them:

- Non-numeric input crashes the programs that read numbers — needs exception handling, which I haven't learned yet.
- Money is stored in `double`. Fine at this scale, wrong for real currency; production code uses `BigDecimal` or integer
  cents.
- `Average.java` hardcodes the divisor `3`, which has to be kept in sync with the number of score variables by hand.
  Arrays fix this in Week 3.

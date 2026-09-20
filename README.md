# java-learning

My Java practice repository. I'm a senior CS student graduating in November 2026, working
toward becoming a Pi-shaped engineer specializing in Java and Flutter. Everything here is
written by hand as I learn — no copied solutions.

## The plan

| Weeks | Focus |
|-------|-------|
| 0 | Setup — JDK, IntelliJ, Git, GitHub |
| 1 | Java basics — variables, types, I/O, `if`/`else` |
| 2 | Loops and methods |
| 3 | Arrays, `ArrayList`, `HashMap` |
| 4 | OOP — classes, objects, constructors, encapsulation |
| 5 | Dart basics |
| 6–9 | Flutter — widgets, state, navigation, local storage, APIs |

After graduation: Spring Boot, SQL and REST APIs, then a full-stack Flutter app on my own
backend.

I keep a running learning log in [PROGRESS.md](PROGRESS.md).

## What's here

All source lives in `src/main/java/org/example/`.

**Week 0**
- `HelloWorld.java` — first program; prints my name and my goal.

**Week 1**
- `Variables.java` — the four core types (`int`, `double`, `String`, `boolean`) and
  `printf` formatting.
- `Greeter.java` — reads a name and age with `Scanner`, prints next year's age.
- `Average.java` — averages three scores; a worked example of the integer-division trap,
  where `(90 + 85 + 82) / 3` silently gives `85.0` instead of `85.7` without a cast.
- `Grader.java` — reads a score and prints a letter grade with an `if`/`else if` chain,
  rejecting anything outside 0–100.
- `TipCalculator.java` — **Week 1 project.** Reads a bill, a tip percentage and a party
  size, then prints the tip, the total, and the per-person share. Validates each input with
  guard clauses before doing any arithmetic.

## Running it

Maven project, Java 25. Open in IntelliJ IDEA and run any class's `main` directly, or from
the command line:

```bash
mvn compile
java -cp target/classes org.example.TipCalculator
```

## Known limitations

Deliberate, and noted so I remember to come back to them:

- Non-numeric input crashes the programs that read numbers — needs exception handling,
  which is past Week 1.
- Money is stored in `double`. Fine at this scale, wrong for real currency; production code
  uses `BigDecimal` or integer cents.
- `Average.java` hardcodes the divisor `3`, which has to be kept in sync with the number of
  score variables by hand. Arrays fix this in Week 3.

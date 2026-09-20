# Learning Log

## 2026-09-19 — Week 0: Setup ✅ (complete)

**Built / did**
- Checked setup: JDK 25 (Temurin LTS, newer than the planned 21, which is fine), IntelliJ Maven project, Git.
- Wrote `HelloWorld.java` myself; it prints my name and my goal.
- Learned that `main` is the entry point: after renaming it to `start`, IntelliJ had nothing to run.
- Made the folder a Git repository and made the first commit (`Week 0: Hello World`).
- Pushed to GitHub: https://github.com/YunVisal/java-learning

**Understood**
- `.gitignore` tells Git which files to skip.
- `main` must be written exactly as `public static void main(String[] args)`.

**Shaky, revisit next time**
- *Why* `target/` is ignored: it holds compiled `.class` files that can be rebuilt, and we commit source code, not build output.
- What `origin` is: a nickname for the GitHub repo URL. My first answer went in a circle.
- `main` has no upstream link yet, so run `git push -u origin main` once.
- Not explained yet: what `static` and `String[] args` mean. That's fine for now; they come later.

**Next session:** Week 1 (Monday), variables and data types.

## 2026-09-20 — Week 1, session 1: variables, types, input

**Built / did**
- Revisited Week 0 gaps: why `target/` is ignored, what `origin` is. Ran `git push -u origin main`.
- Wrote `Variables.java`: one `int`, `double`, `String`, `boolean` about himself, printed with
  `printf` and correct format specifiers (`%s %d %.1f %b`).
- Wrote `Greeter.java`: reads a name and an age with `Scanner`, prints next year's age.
- Fixed the `main` signature in both `Variables.java` and `HelloWorld.java` to the full
  `public static void main(String[] args)`.

**Understood**
- `target/` is ignored because it is *generated* — a build can recreate it. Commit what you
  write by hand, ignore what a command can rebuild. (It holds his own compiled `.class` files,
  not dependencies; those live in `~/.m2`.)
- `origin` is a saved nickname for the GitHub repo URL.
- A declared type is a promise that holds for the variable's whole life. Gave a correct concrete
  example of what it catches: `age = 3.5;` fails to compile.
- Reached for `Integer.parseInt(scanner.nextLine())` rather than `nextInt()`, which avoids the
  leftover-newline trap. Good instinct — he should keep reading whole lines.
- `%n` vs `\n` in `printf`; `print` vs `println` for prompts.

**Corrections made**
- His Week 0 note said `main` must be written *exactly* as `public static void main(String[] args)`.
  Java 25 actually allows shorter forms, which is why his `static void main()` ran. But Java 17/21
  require the full form, so use it for portability.
- His first answer on what the compiler gains was circular ("it catches compile-time errors") —
  same looping pattern as the `origin` answer in Week 0. Flagged it: if an answer reuses the
  question's words, it isn't an answer yet. His follow-up example was correct.

**Shaky, revisit next time**
- The circular-answer habit. Keep pushing for concrete examples.
- `Greeter.java` crashes with `NumberFormatException` on non-numeric input. Deliberately left
  unfixed — it's the lead-in to `if`/`else` and validation next session.
- Not covered yet: closing a `Scanner` / try-with-resources; what `static` and `String[] args` mean.

**Next session:** Week 1 practice — integer division (`7 / 2` vs `7.0 / 2`), then `if`/`else`,
building toward the tip-calculator project on the weekend.

## 2026-09-20 — Week 1, session 2: division and if/else

**Built / did**
- Predicted `7 / 2` → `3` and `7.0 / 2` → `3.5` correctly with no prompting.
- Wrote `Average.java`: three `int` scores averaged as a `double`, printed to one decimal.
- Wrote `Grader.java`: reads a score with `Scanner`, prints a letter grade via an
  `if` / `else if` chain, and rejects scores outside 0–100 before grading.

**Understood**
- `double result = 7 / 2;` prints `3.0`. He explained *why* unprompted and correctly: the
  right-hand side is evaluated on its own using its operands' types, so `int / int` is `int`,
  and the widening to `double` happens only at assignment — too late.
- `int` division truncates, it does not round.
- Placed the `(double)` cast correctly: `(double)(sum) / 3`, not `(double)(sum / 3)`.
- Saw the trap both ways: with the cast `85.7`, without it `85.0` — a wrong answer that raises
  no error at all. He seemed to take the point that silent wrongness beats a crash for danger.
- `if` / `else if` stops at the first true branch, so earlier conditions carry the upper bound
  of later ones — which is also why reordering the chain silently breaks it.
- Validity check must come first in the chain, since every later branch assumes a valid score.

**Corrections made**
- My error, not his: I gave him scores `90/85/80` to expose the integer-division trap, but they
  average to exactly `85`, so the trap stayed hidden. Changed `score3` to `82` to make it show.
  Pick numbers that actually discriminate when setting up a demonstration.
- His first invalid-score message was `"greater than 100 or less than 0"` — a restatement of the
  `if` condition rather than something useful to a user. Same underlying habit as his circular
  answers: describing the mechanism instead of the meaning. Flagged the connection explicitly.
- Brace/space style: fixed `if(` → `if (` and moved `else` onto the closing-brace line, then it
  regressed on the next edit. Pointed him at Cmd+Opt+L.

**Shaky, revisit next time**
- The restate-instead-of-explain habit. It has now shown up in three places (`origin`, "what does
  the compiler gain", the error message). Keep pushing for concrete specifics.
- Magic number `3` in `Average.java`, hardcoded to match the number of score variables. Named as
  a coupling problem that arrays solve in Week 3; not fixed.
- `Integer.parseInt` still crashes on non-numeric input in both `Greeter` and `Grader`. Still
  deliberately unfixed — needs exception handling, which is past Week 1.
- Scanner never closed anywhere; try-with-resources not covered yet.

**Next session:** Week 1 project — `TipCalculator.java`, being built in three steps. Step 1
(read bill and tip percent, print tip and total to two decimals) has been given.

## 2026-09-20 — Week 1, session 3: tip calculator project ✅ (Week 1 complete)

**Built / did**
- `TipCalculator.java`, built in three steps: (1) bill + tip percent → tip and total,
  (2) party size → per-person share, (3) input validation.
- Adopted the `tipPercentage / 100.0` idiom over casting the variable.
- Turned on IntelliJ format-on-save (Settings → Tools → Actions on Save → Reformat code).
- `README.md` added at the repo root.

**Understood**
- Avoided the integer-division trap unprompted this time — no reminder needed.
- Wrote the validation as **guard clauses**: check each value right after reading it, `return`
  immediately on failure. He arrived at this himself rather than nesting the work inside one
  big `if (valid)` block. Named the pattern for him and explained why it scales better.
- Floating-point division by zero yields `Infinity` rather than throwing, unlike `int` division
  which throws `ArithmeticException`. He ran it with 0 people and saw `$Infinity` printed as if
  it were a real answer — third instance this week of "the dangerous failure is the quiet one."

**Corrections made**
- Missing `%n` on the last `printf` — the identical bug he fixed in `Variables.java` in session 1.
  Pointed out that new last lines are where he stops checking consistency.
- `if(` spacing regressed for the third time. Rather than correcting it again, had him enable
  format-on-save so it stops being a thing he has to remember.
- Error-message phrasing: `"People mustn't less than 1"` → `"Number of people must be at least 1."`
  Covered naming the field rather than a bare noun, plain positive phrasing, and consistent
  punctuation across all messages.

**Note on the README**
- I offered the README as his task; he asked me to write it instead, so I did, and told him to
  edit it into his own voice. Worth revisiting — it's the first thing a recruiter reads on the
  repo and it currently isn't his writing. Technical writing is a skill he should practice
  rather than delegate; try assigning the Week 2 README to him again.

**Shaky, revisit next time**
- The restate-instead-of-explain habit — quieter this session, but keep testing for it.
- Still unfixed by design: `parseInt`/`parseDouble` crash on non-numeric input; `Scanner` never
  closed; `double` used for money; magic `3` in `Average.java`.
- Not yet explained: what `static` and `String[] args` mean. He has now written them many times
  without knowing what they do. Worth covering early in Week 2, alongside methods.

**Next session:** Week 2 — loops (`while`, `for`) and methods. Project: number-guessing game.
Methods are the natural moment to finally explain `static`, parameters and return types, and to
let him pull the repeated validation in `TipCalculator` into a reusable method.

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

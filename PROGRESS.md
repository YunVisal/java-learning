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

## 2026-09-21 — Week 2, session 1: loops (`while`, `for`, sentinel)

**Built / did**
- `Countdown.java`: a `while` loop printing 5→1 then `Liftoff!`. Correct on the first try.
- `AverageLoop.java`: reads N scores with a loop and averages them — the loop-based answer to the
  hardcoded `3` in `Average.java`. Started as a countdown, converted to counting up, then to `for`.
- `ScoreTally.java`: a sentinel loop reading scores until `-1`, reporting count and average.
  Took five review rounds.

**Understood**
- The three parts every loop needs: start, condition, change — and that missing the change hangs
  the program.
- Off-by-one: predicted `timer >= 0` correctly. Took the point that a one-character change to the
  boundary shifts the count by one with no error anywhere.
- Scope: predicted correctly that `System.out.println(i)` after a `for` loop won't compile, and
  accepted that this is a feature — the `while` version leaked `remaining` past its useful life.
- Why `0.0 / 0` is `NaN` but `5.0 / 0` is `Infinity`.
- An average is a **result**, computed once after the loop — not state maintained inside it.
  He had been recomputing it every iteration, which forced a fake `0.0` initial value, which
  produced the bug below.
- A pre-initialized default can be worse than a crash: `AverageLoop` showed `NaN` on empty input
  (obviously broken), while `ScoreTally` showed `Average score: 0.0` (a plausible-looking lie).
  Fourth instance of "the dangerous failure is the quiet one."
- Reached for `do...while` unprompted, before it was taught.

**Corrections made**
- **`for` vs `while` took four attempts.** His first three answers were rules restated from the
  syntax ("use `for` when you know how many times") — including one that handed my own nudge
  straight back. Only landed when given a concrete scenario (a password prompt) and asked for
  the trip count: "we don't know, it depends on the user." That is the real distinction —
  `for` = trip count known before the first pass, `while` = ends on an event.
- **Dead code in the sentinel loop.** He had both `if (input == -1) break;` and
  `} while (input != -1);`. The `break` makes the `while` condition unreachable-false — it looks
  like it controls the loop and does nothing. He removed it correctly once traced.
- **Naming:** `totalLoop` → `remaining` → a counter that counts up (so `remaining` became a lie);
  `numberOfLoop` → `numberOfInput`. Recurring pattern: he names variables after the loop
  machinery rather than the thing being counted.
- **Leftover declaration:** `int input = 0;` stayed outside the loop after the `do...while`
  condition that needed it was removed. Same lesson as `i` disappearing into the `for` header.

**Shaky, revisit next time**
- **The restate-instead-of-explain habit — this is now the dominant issue.** Six instances to
  date (`origin`, the compiler question, the `Grader` message, "Total of input", "Stopped!",
  "Bye, bye!"). The `ScoreTally` empty-case message took four separate rounds: he kept
  *relocating* the message instead of *rewriting* it, and swapped one non-answer ("Stopped!")
  for another ("Bye, bye!"). What finally worked was an explicit test he can self-apply:
  *show the message to someone who has never seen the code — can they say what happened to their
  data?* Reuse that test rather than re-explaining.
- English in user-facing strings: "No score was entered, cannot computed", "Number of score".
  Worth correcting each time — it is the visible surface of his work.
- `if (` spacing held this session, but he never confirmed whether format-on-save is actually
  enabled. Ask once.
- Still unfixed by design: `parseInt` crashes on non-numeric input everywhere; `Scanner` never
  closed; `double` for money; magic `3` in the original `Average.java`.
- Not yet explained: `static`, `String[] args`.

**Next session:** Week 2 continues — **methods**. This is the moment to explain `static`,
parameters and return types, then have him extract the repeated `Integer.parseInt(scanner.nextLine())`
+ validation from `TipCalculator` into a reusable method. The number-guessing game is the weekend
project; his sentinel loop in `ScoreTally` is already the right shape for its main loop.
Assign the Week 2 README to him this time — the Week 1 one is my writing, not his.

### Addendum — he asked to work on the restate-instead-of-explain habit

He raised this himself after the session wrap, unprompted. Worth noting: he is now tracking his
own weaknesses, which is a change.

**What was established**
- The habit does *not* show up on "what does X do" questions — he handles those fine. It appears on
  **"why" questions** and in **user-facing text**. My first drill ("what does `break` do?" with the
  word banned) was badly targeted and he correctly pushed back that he had answered the question
  asked. He was right; I conceded it.
- The rule he now has: **answer with an instance, not a category** — every abstract answer must be
  followable by "for example, if…" with real values. Its value is diagnostic: if the instance won't
  come, the understanding is a memorised phrase.
- He produced a clean non-circular answer on the second drill (why the `numberOfInput > 0` guard
  exists), naming the *consequence* — a misleading result reaching the user — rather than restating
  the condition. Real progress against the `Grader` message from Week 1.

**Two errors inside that otherwise-good answer**
- Called `-1` a "program exit code." It is a **sentinel value**; an exit code is what a process
  returns to the OS. Reaching for a technical-sounding term instead of the true one is a variant of
  the same habit.
- Claimed the unguarded version prints `0`. It prints `NaN`. He reasoned correctly from a mental
  model two edits stale — the version that still had `double averageScore = 0.0;` at the top.

**The more useful lesson (his second push-back)**
He objected to being asked to run the program, saying his purpose was to improve reasoning, not to
test. Partly fair — I should have asked him to derive it, and did. But the point stands and landed:
his wrong claim came from correct reasoning over a stale premise, which thinking harder cannot
catch, because it re-runs the same premise. Framed for him as a second self-check:
**is the version in my head the version on disk?**

**How to teach this going forward**
- Target "why" questions and user-facing strings, not "what" questions.
- State the purpose of a drill *before* setting constraints. He disengages from constraints that
  look arbitrary, and he is right to.
- He pushes back when he thinks an exercise is pointless. This is good. Answer the objection
  directly rather than repeating the instruction.

## 2026-09-22 — Week 2, session 2: methods, `static`, and refactoring `TipCalculator`

**Built / did**
- `Methods.java`: `calculateTipAmount(billAmount, tipPercentage)` — returns the tip, prints nothing.
  Correct on the first pass, including `%%` for a literal percent sign, which he worked out alone.
- `TipCalculator` refactored in two steps. Step 1: extracted `readInt(scanner, prompt)` — prompt,
  read, parse — replacing two near-identical blocks. Step 2: added a minimum bound and a loop, so
  the method re-prompts until the value is valid and the two `if` guards in `main` disappeared.
- `static`, instance methods and `String[] args` finally explained.

**Understood**
- **`for` vs `while` has landed.** Given a fresh pair of scenarios (times table / menu until Quit) he
  named the deciding fact himself — trip count known before the first pass vs. ends on an event.
  This took four rounds last session and none this time.
- A method's four parts, and that `main` is just a method Java happens to call for you.
- `static` = belongs to the class, not to an object — anchored on two calls he already uses:
  `scanner.nextLine()` (instance) vs `Integer.parseInt(...)` (static). Predicted the
  `cannot be referenced from a static context` error and explained it without circling.
- **Return vs print**: cited an actual line of his own code as the instance. Sharpened for him —
  assigning to a variable is the symptom; the real point is the printing version *throws the answer
  away*, so `billAmount + calculateTipAmount(...)` becomes unwritable.
- Traced that `return` inside `readInt` goes back to `main` and continues, so the guard guards
  nothing — and that a placeholder return value would produce a plausible total from a number the
  user never typed. Fifth instance of "the dangerous failure is the quiet one."
- Reached "loop until the input is valid" himself once the constraints were laid out.
- Read the four-parameter trade-off correctly: two adjacent `String` params can be swapped with no
  compiler complaint, but a method-built message loses the domain word ("Number of people"). He
  kept the four-parameter version. Told him explicitly that this is a judgement call, not a defect.

**Corrections made**
- **Commented-out old code left in the file** after Step 1 — the replaced lines kept as comments.
  Explained why Git makes that a liability, not a safety net. Same shape as the leftover
  `int input = 0;` in `ScoreTally`: the old thing survives the change that made it pointless.
- **New pattern, and the most useful thing to come out of tonight: he corrects by *appending*, not
  by *replacing*.** `message` → I asked for `prompt` → he wrote `promptMessage`, and kept it through
  a second ask. It took three asks. This is the same move as the `ScoreTally` empty-case message
  last session, where he kept relocating the string instead of rewriting it. It reads as agreement
  while conceding nothing. Told him directly that pushing back is welcome but half-keeping is not.
  **Watch for this specifically: after a correction, check whether the old thing is actually gone.**
- Money formatting: `%.2f` for the tip but `%.1f` for the bill in the same line — `$60.0` isn't how
  money is written. His weakest surface remains user-facing output.
- `double billAmount = 60;` → `60.0`. Compiles either way; the point was making intent visible
  rather than relying on a silent widening.
- "object method" → **instance method**. Same reflex as "exit code" for "sentinel value" last
  session: reaching for a plausible-sounding term instead of the real one.
- `readIntegerInput` → `readInt` ("read" and "Input" say the same thing twice).
- The `if (invalid) { print } else { return }` shape → guard clause. He owns this pattern already
  from Week 1; he just didn't recognise the situation. Flipped it to `>=` cleanly.
- Asked for the two problems with a bare `return` inside `readInt`; he gave one correct answer and
  then answered a different question (differing bounds/messages). Got the second on one nudge.

**Shaky, revisit next time**
- **The append-instead-of-replace habit.** This is now the sharper diagnosis of what I had been
  filing as "restate instead of explain" — both are modifying around the edge of a thing rather
  than replacing it. Easier to test for: after any correction, is the old version *gone*?
- The restate habit itself was much quieter this session. Four separate answers came back as
  concrete instances without being asked twice. Real improvement — keep testing, stop expecting it.
- `billAmount` still reads and validates the old way, because it's a `double` and `readInt` can't
  take it. Left deliberately — the fix is overloading or generics, neither covered yet. Good
  opening for a future session on why two methods can share a name.
- Still unfixed by design: `parseInt` crashes on non-numeric input everywhere; `Scanner` never
  closed; `double` used for money; magic `3` in `Average.java`.
- Format-on-save is enabled but never fires, because he doesn't explicitly save. Told him to hit
  Cmd+S. Check next session whether the habit stuck.

**Next session (Thursday):** Week 2 practice + review — exercises on methods, one at a time.
Good candidates: extract the sentinel loop in `ScoreTally` into a method that returns the average;
write a method that returns a `boolean`; and one that takes no parameters, so he sees that
parameters are a choice rather than a requirement. Then the weekend project, the number-guessing
game — his `ScoreTally` loop is already the right shape for its main loop.
**The Week 2 README is his to write, not mine.** The Week 1 one is my writing and shouldn't stay
the only voice on the repo.

## 2026-09-23 — Week 2, session 3: methods practice, `boolean` returns, constants

**Built / did**
- `ScoreTally` rebuilt end to end, all of it his typing:
  - Loop extracted into `readScoresAndComputeAverage()` — reads until the sentinel, prints the
    count, returns the average. `main` is now three lines and a `printf`.
  - `isInputValid(score, lowerBound, upperBound)` — a one-line `boolean` method, no printing.
  - Out-of-range scores rejected and re-prompted instead of silently added to the total.
  - Four constants introduced: `MIN_ALLOWED_SCORE`, `MAX_ALLOWED_SCORE`, `QUIT_INPUT`,
    `AVERAGE_NOT_COMPUTED`.
  - Empty-case message rewritten to tell the user what to do, not just what failed.
- `static final` and `SCREAMING_SNAKE_CASE` taught for the first time.

**Understood**
- **He hit the "a method returns exactly one value" wall on his own**, before writing a line of
  code, and asked about it directly instead of guessing. Best moment of the session.
- Named the sentinel principle himself once pointed at his own `-1`: a sentinel must come from
  **outside** the range of real values. Applied it correctly to replace `0.0` as the "no data"
  return, which a genuine all-zeros average was silently swallowing.
- Saw why a `boolean` method shouldn't print, once given the instance (500 scores from a file →
  500 error messages). Could not produce the scenario unprompted.
- Duplicated magic numbers: named the risk as "change twice, values mismatch" without help.
- Grasped that `-1` in the loop and `-1.0` in the return are two different meanings that happen
  to share a value — after being shown, not before.

**Corrections made**
- **Partial compliance at exercise scale, which is new and worse than the line-level version.**
  Exercise 2 explicitly required the validity decision to live in a `boolean` method. First
  attempt had no such method at all — an inline `if`, and only the lower bound. Task re-issued.
- **Append-instead-of-replace, three times in one session:**
  1. Message fix: `"besides -1"` → asked to replace → `"any positive number besides -1"`
     (an impossible exception; `-1` isn't positive).
  2. `input < -1` survived into `isInputValid` as `score < -1` while its own message said
     "must be at least 0" — condition and message contradicting each other.
  3. `QUIT_INPUT` reused for the "no average" return, collapsing two meanings into one name.
- **Two edits that changed nothing observable and were wrong anyway** — worth naming as a pair:
  after separating the constants he wired them backwards (`input == AVERAGE_NOT_COMPUTED` in the
  loop), and both constants are `-1`, so the program behaved identically. Sixth and seventh
  instance of "the dangerous failure is the quiet one," and the first where *his own fix* was
  the quiet failure.
- Asked what `isInputValid(-1)` returns; he answered about what the **caller** does instead —
  sidestep, same shape as answering a different question last session. Then said `false`; traced
  `-1 < -1` and self-corrected to `true` in one step.
- `score > lower && score < upper` — off-by-one, rejected exactly `0` and `100`. Found it fast
  when asked what happens to a score of 100.
- `println` → `printf` dropped the newline; second attempt put `%n` **before** the final period,
  printing a lone `.` on its own line.
- "program exit condition" for **sentinel** — third variant of reaching for a plausible-sounding
  term (after "exit code", "object method"). Named it again.
- Over-generalised unasked: parameterised `isInputValid`'s bounds when a one-word fix was asked
  for. Not wrong, but it moved the domain knowledge out to the call site as bare numbers, which
  is what forced the constants conversation.

**What worked as a teaching move**
Twice, when he was stuck editing the artifact, taking him **off the code and onto a plain-English
sentence** produced a clean answer immediately:
- "What are the two things a user must do?" → *"enter at least one score, then -1 to finish"* —
  after three failed rewrites of the same string.
- "Say in one sentence what that value tells `main`" → *"no average could be computed"* →
  `AVERAGE_NOT_COMPUTED`.
**Use this deliberately.** The naming and wording failures are not vocabulary problems; he can
say the right thing, but not while looking at the wrong version of it.

**Shaky, revisit next time**
- The diagnosis has sharpened again: it is not comprehension, it is **finishing**. He reaches the
  concept fast — often alone — and then lands the edit at about 80% and doesn't re-read the
  result. Every correction tonight needed a second pass. **Have him read the changed line back
  before saying "done."**
- Exercise 3 (a method with no parameters) was never set — `readScoresAndComputeAverage()` turned
  out to be one, so the point was made incidentally. Make it explicit if it doesn't stick.
- `readScoresAndComputeAverage` still both reads input and prints the count. He knows the name is
  honest about it; he hasn't yet felt why a method doing two jobs costs anything.
- `isInputValid` takes a param called `score` — the name should probably say score too.
- Still unfixed by design: `parseInt` crashes on non-numeric input; `Scanner` created *inside* the
  method and never closed (he can state why passing it in is better, but his code doesn't); `==`
  on `double`s; `double` for money in `TipCalculator`; magic `3` in `Average.java`; `billAmount`
  can't use `readInt` (needs overloading).
- Message wording still clunky: "Score should be at least 0 and cannot be greater than 100."
- **Cmd+S habit: never verified this session.** Check next time.

**Next session (weekend):** the Week 2 project — the **number-guessing game**. His `ScoreTally`
loop is the right shape for its main loop, and he now has `boolean` methods and constants, which
covers everything the game needs. Set it as one task with a stated finished behaviour, then stay
out of the way.
**The Week 2 README is still his to write, not mine.**

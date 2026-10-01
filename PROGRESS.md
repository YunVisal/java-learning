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

## 2026-09-24 — Week 2, session 4: number-guessing game (project, core done)

**Built / did**
- `GuessingGame.java`, designed and typed by Visal:
  - Random secret in the game's range via `random.nextInt(MIN_GAME_RANGE, MAX_GAME_RANGE + 1)`.
  - Guess loop with "Too low" / "Too high"; out-of-range guesses rejected and **not counted**.
  - Methods: `generateSecretNumber()`, `readGuessNumber(scanner)`, `isGuessNumberValid(number)`,
    `play(scanner)` (one round), `wantsToPlayAgain(scanner)` (returns `boolean`).
  - Play-again loop in `main`; strict `y`/`n` input with a message that names the valid choices.
- Confirmed format-on-save + Cmd+S is working.

**Understood**
- `nextInt(origin, bound)` has an exclusive upper bound, so `+ 1` is needed. Got it right unaided.
- Bounds check `>=` / `<=` correct at exactly 1 and 100 first time (last session was off by one).
- Passed the `Scanner` into methods instead of creating it inside. Last session this was known but
  not practiced; now it's in the code.
- Put the counter increment *after* the validity `continue`, so rejected guesses don't count.
- Why `while (guess != secretNumber)` can't work: `guess` is scoped inside the loop body. Linked
  moving it out (`int guess = 0;`) to the fake-default bug from `ScoreTally`.
- Split responsibilities correctly and unprompted: `play` = one round, `main` = the session, so
  the play-again question belongs in `main`.
- Replacing a returned `String` with a `boolean` so the caller doesn't re-decide: named the type
  and a good name (`wantsToPlayAgain`) immediately.
- Applied the line-20 lesson unprompted later: the `(y/n)` prompt and error message both use the
  constants.

**Corrections made**
- Line 20 had `"1-100"` hardcoded next to the constants — the "change it twice" risk again. Fixed.
- **Read-back rule:** asked to read changed lines back before saying "done". First time, Visal read
  the program's *output* rather than the code, which can't show whether the fix was real. After
  being told, read code every time.
- **80%-fix pattern still present:** prompt reworded (`"Number: "` → `"Enter the guess number: "`)
  without adding the range, which was the actual ask; constant rename first only dropped `VALUE`.
  Fixed on the second pass. `else` removed, then came back in the next rewrite; `wantsToPlayAgain`
  written as `wantToPlayAgain`. The read-back rule catches these; keep using it.
- Constant naming: stuck on "what do 1 and 100 describe?". One nudge (both the computer and the
  player use them) → "the game's range" → `MIN_GAME_RANGE` / `MAX_GAME_RANGE`. The plain-English
  sentence move worked again.
- Explained a silent re-prompt from the programmer's view ("because I use `.equals`") instead of
  the player's. Also called `.equals` an "operator" — it's a method. Same plausible-term habit.
- `"Invalid choice."` → message that tells the player what to type, built from constants.

**Judgement calls Visal made (respect these, don't reopen)**
- Play-again input stays strict lowercase `y`/`n`; `Y` is rejected. **I raised it a second time
  after Visal had decided, and Visal was rightly angry about it.** My mistake. Once Visal makes a
  judgement call, state the cost once and drop it.
- Kept an explicit `if / else if / else` chain in `wantsToPlayAgain` rather than a guard clause, so
  all three outcomes are visible. Valid style choice.

**Shaky, revisit next time**
- Finishing/read-back: better once prompted, but nothing is fully fixed on the first attempt yet.
- Reaching for plausible-sounding terms ("operator" for method, earlier "exit code").
- Still unfixed by design: `parseInt` crashes on non-numeric input (the game dies on `abc`);
  `Scanner` never closed; `double` for money in `TipCalculator`; magic `3` in `Average.java`.
- Minor wording: `"The number should be at least %d and cannot be greater than %d."` is clunky;
  `"Correct."` is a flat win message. Not pushed tonight.

**Next session (weekend):** ship Week 2. Visal writes the Week 2 README section themselves; the
Week 1 README is still my writing. Then Week 3 (arrays, `ArrayList`, `HashMap`) — the magic `3`
in `Average.java` is the natural opening.

## 2026-09-24 — Curriculum update (no coding)

The plan in CLAUDE.md changed so every project is finance-flavored (banks/fintechs portfolio).
What this changes for the open threads above:
- **Week 3 project is now a transaction ledger** (record income/expenses, totals by category, balance),
  not a generic data exercise. The magic `3` in `Average.java` is still a good opening for arrays,
  then move to finance examples.
- **"`double` for money" is no longer just "unfixed by design".** Week 4 (bank account system) is where
  it gets fixed: learn why money uses `BigDecimal`, then revisit `TipCalculator`.
- **READMEs are now formal English writing practice every Sunday**: what was built, why the design
  choices, what to improve. This reinforces the note that Visal writes the Week 2 README; the Week 1
  README is still my writing.
- Weekly rhythm and tutor rules unchanged.

**Next session (weekend):** unchanged. Ship Week 2 (Visal writes the README), then Week 3: arrays,
`ArrayList`, `HashMap` → transaction ledger project.

## 2026-09-25 — Week 2 ship: README (no coding)

**Built / did**
- Visal wrote a first draft of the Week 2 README section: a line for each file plus notes on `GuessingGame`.
- After my review, Visal gave up on revising it and asked me to write it. **I wrote the final Week 2 section**
  (and changed "past Week 1" in Known limitations). The Week 2 README is now my writing, like Week 1's.

**Understood**
- Visal's draft got the substance right: the `play`/`main` split and why (single responsibility), and a
  sensible improvement (a best score).

**Draft problems (for reference, not to reopen)**
- Accuracy: `Countdown` described as "5 steps" (it prints 5..0, six numbers); `Methods` described the concept
  of a method, not what the file does; `ScoreTally`'s 0–100 validation left out; the `TipCalculator` refactor's
  behaviour change (re-prompt instead of quitting) missed; the game's range not stated.
- English: the same error many times, singular subject + verb without -s ("which compute", "Player guess",
  "user want"); missing articles; "response" used as a verb; a comma between subject and verb.
- Format-on-save re-wrapped the whole README to 120 columns, so the diff is noisy.

**My mistake this session**
- My review listed ~15 issues in one message, against the one-thing-at-a-time rule. That's what made Visal
  give up. **Next time, review one issue at a time, biggest first**, even when there are many.

**Shaky, revisit next time**
- Singular subject → verb with -s. It's the most frequent English error, so make it the focus of the next README.
- Describing what code *does*, not what the concept *is*.
- Reasoning question left unanswered: what should a recruiter learn from a project paragraph that a file list
  can't show?

**Next session:** Week 3, arrays, `ArrayList`, `HashMap`. Open with the magic `3` in `Average.java`, then
build toward the transaction ledger. Week 3's README is Visal's to write, with reviews one point at a time.

## 2026-09-25 — Week 3, session 1 (Monday): arrays

**Built / did**
- Warm-up: `.equals` is a method, not an operator. Visal's clue was "parentheses"; completed it to
  "dot + parentheses" (`(a + b)` has parentheses too).
- `ArrayBasics.java`: an `int[] amounts`; printed the first and the last amount (`amounts[amounts.length - 1]`),
  a total with a `for` loop over `.length`, and the largest transaction (starts at `amounts[0]`, loop from `i = 1`).
- Output labels added after review (bare numbers → `First amount: …`, `Total spent: …`).
- `Average.java`: **magic `3` fixed**. The scores are now in an array, summed in a loop, divided by `scores.length`.
- Ran `amounts[amounts.length]` on purpose and read the `ArrayIndexOutOfBoundsException`.

**Understood**
- Why arrays: you can't loop over `score1`, `score2`, `score3`. Indexes start at 0, so 5 items → indexes 0–4.
- `i < 5` vs `i < amounts.length`: the hard-coded number goes out of sync, and a *silently* wrong total is the
  worst bug in finance.
- Integer division: `257 / 3` → `85` → `85.0`. Answered correctly.
- Max: starting at `0` fails for all-negative amounts (prints a number not in the array); start at `amounts[0]`.

**Shaky, revisit next time**
- **`length - 1` in the wrong place.** Wrote `i < amounts.length - 1` in the max loop, which skipped the last
  item. It was hidden because the test data had the max in the middle. Needed two prompts: the `p` typo was fixed
  first but the `- 1` stayed, and the suggested test wasn't run. Rule: `length - 1` is for *indexing the last
  element*, `i < length` is for *looping*. Test values at the edges (first/last).
- Read-back before "done": a typo (`1p`) sent as done. Same habit as Week 2; still the main thing to work on.
- English: singular subject → verb -s ("longer code lead", "which make"). Still the most frequent error.
- Reasoning answers are right but loose ("every index need to minus 1", "amounts[0] is already max").
  Push for precise wording.

**Next session (Tuesday): practice.** Start with a quick edge-case check on the max loop (max in the last position).
Then `ArrayList`: why a fixed-size array doesn't fit a ledger where transactions keep being added. Exercises one
at a time, finance-flavored. Possibly introduce the for-each loop.

## 2026-09-26 — Week 3, session 2 (Tuesday): ArrayList, for-each, try/catch

**Built / did**
- Warm-up: `length - 1` indexes the last item; `i < length` loops over every item. With 5 items, `i < length - 1`
  stops at `i = 3` and skips `amounts[4]`. Answered precisely.
- `Ledger.java`: an `ArrayList<Integer>` of 5 expenses; printed the count (`size()`), first and last (`get(0)`,
  `get(size() - 1)`), the total with an index loop and again with a for-each loop, then `remove(1)` and printed
  each item with its index.
- `ExpenseInput.java`: reads expenses until `0` (not added), with input in its own `readExpense` method. Bad input
  (`abc`, `12.50`) is caught with `try`/`catch (NumberFormatException)` and asked again (`while (true)` + `return`).
  Prints count, total (for-each) and largest; an empty list is handled with `isEmpty()`.

**Understood**
- `ArrayList` grows; arrays don't. `Integer`, not `int`, inside `<>`.
- `length` is a field (no parentheses); `size()` is a method.
- For-each hides the index: use an index loop when you start at `i = 1`, need the position, or compare neighbours.
- `return` inside `try` ends the whole method, not just the loop. His walk-through of the `try`/`catch` flow was
  precise and correct.
- Exceptions are for surprises. The first "largest" version caught `NoSuchElementException` for an empty list;
  rewritten with `if (expenses.isEmpty())`, because an empty ledger is a normal case you can check.
- `remove(index)` shifts every later item one place left (predicted correctly; wording needed sharpening).

**Shaky, revisit next time**
- **Read-back before "done"**: typo `toalExpenses`; leftover `readExpense` + `Scanner` import in `Ledger`, and
  the import survived the first "done". Still the main habit. Tips given: Shift+F6 rename, Ctrl+Alt+O imports.
- **Test data**: first `Ledger` data had `2` at index 1 and at the end, so a wrong index wouldn't show. Rule:
  every position gets a different value.
- `Ledger.java` got overwritten once while creating `ExpenseInput` (the `add` lines were lost → exception). Fixed.
- English: "which make" → "makes" (the -s again), "is threw" → "is thrown", "due to + clause" → "because".
- Not handled yet: negative expenses are accepted; the removing-inside-a-loop bug (mentioned, not shown).

**Next session (Thursday): practice + review.** Harder exercises: reject negative amounts; income vs expenses
(signs or two lists) and a balance; then introduce `HashMap` (category → total) as the step toward the Week 3
transaction ledger project.

## 2026-09-29 — Week 3, session 3 (Thursday): validation, balance, HashMap

**Built / did**
- Warm-up: test data `[5, 2, 8, 2]` hides a `get(1)`-instead-of-last bug. Rule sharpened to "every position gets
  a different value".
- `ExpenseInput.java`: negative amounts are rejected with an `if` inside `readExpense` and asked again.
- `Balance.java`: signed amounts (option A: positive = income, negative = expense, `0` stops). Prints total income,
  total expenses (`Math.abs`) and the balance. Tested: 500, -20, abc, -35 → 500 / 55 / 445, which is correct.
- `CategoryTotals.java`: category → total map. The first version used `merge(..., Integer::sum)`, which Visal
  couldn't explain, so it was rewritten with `get` + `put`, then `getOrDefault(key, 0)` for every line so the order
  doesn't matter. Switched `HashMap` → `TreeMap` for sorted output.

**Understood**
- `parseInt("-5")` doesn't throw, so a negative amount *must* be an `if`. (Correction: a caught exception doesn't
  end the program. `if` is for expected, checkable cases; exceptions are for what you can't easily check first.)
- Signed-amount weakness: a missing minus sign silently puts the amount in the wrong category (the balance is off
  by 2× the amount).
- `get` on a missing key returns `null`, and `null + 35` → `NullPointerException`. Predicted correctly.
  `getOrDefault(key, 0)` fixes it.
- For-each over `entrySet()`: each `total` is one `Map.Entry`. For the food entry, `getKey()` → "food" and
  `getValue()` → 55.
- `HashMap` has no order; `LinkedHashMap` keeps insertion order; `TreeMap` sorts. Predicted TreeMap's order correctly.

**Shaky, revisit next time**
- **Using code he can't explain** (`merge`, `Integer::sum`; the `entrySet` loop was first explained as "EntrySet
  type"). He answered "I'm not sure" honestly, which is good. Keep asking him to explain any untaught code.
- Read-back: unused `ArrayList` import in `Balance` (found it quickly when asked). He later removed the `HashMap`
  import on his own, which is progress. No test output pasted with the first "done".
- English: "due to + clause" again (fixed to "because"); "which cause" → "causes"; "the item … and the last item
  has" → "have". He wrote "returns" correctly later.
- Unfinished exercise: `addExpense(map, category, amount)` method to replace the four repeated lines.

**My mistake this session**
- Sent a task message with a design choice, a reasoning question, a test and an English note all at once. Visal
  called it out. One action per message, including for tasks.

**Next session (Saturday): build.** Start with the unfinished `addExpense` method, then begin the Week 3 project,
the transaction ledger: read category + amount from the user, keep totals per category, show the balance.

## 2026-09-30 — Week 3, session 4 (short session): addExpense method

**Built / did**
- Warm-up: `getOrDefault("food", 0)` returns `0` the first time (no key yet → fallback) and `20` the second time
  (key exists → its value). Answered correctly and clearly.
- `CategoryTotals.java`: finished the leftover exercise. `static void addExpense(Map<String, Integer> map,
  String category, int amount)` does the get-or-default + put; the four repeated lines in `main` are now four calls.
- Review fix: parameter type `TreeMap` → `Map`, so any map (`HashMap`, `LinkedHashMap`, `TreeMap`) can be passed.

**Understood**
- A `void` method can still change the map because the caller and the method both refer to the same object.
  Precise wording: Java passes a *copy of the reference* ("always pass-by-value"). `put` changes the shared map,
  but reassigning the parameter inside the method wouldn't change `main`'s variable.
- Declare parameters with the interface (`Map`) when the method only needs what every map can do.

**Shaky, revisit next time**
- Said "passed by reference". The idea is right, but the term is technically wrong for Java. Worth a quick check.
- Called the `TreeMap` a "hashmap" in his answer. Small, but keep the three map types distinct.
- English: "the getOrDefault try" → "tries" (singular subject → verb -s, still the most frequent error);
  "the key is exist" → "the key exists".
- Test output not pasted (my instruction was unclear; he didn't know what to paste). Next time ask for the
  console output explicitly.

**Next session (moved from Saturday): build.** Start the Week 3 transaction ledger project: read category +
amount from the user (reuse `readExpense`-style validation and `addExpense`), keep totals per category, show
income, expenses and the balance. Then the README, with one review point at a time.

## 2026-10-01 — Week 3, session 5 (build): transaction ledger project

**Built / did**
- Warm-up: if `addExpense` did `map = new TreeMap<>()`, `main`'s map would not change. Answered correctly: only
  the copy of the reference is pointed at the new map. ("pass-by-value of the reference" now used correctly.)
- `TransactionLedger.java` (Week 3 project), built step by step:
  - `readType` (income/expense/done, any capitalization, retries on invalid input), `readCategory`,
    `readAmount` (retries on non-numbers, rejects zero and negatives), `addToTotal` (get-or-default + put).
  - `EXIT_WORD`, `INCOME_TYPE_WORD`, `EXPENSE_TYPE_WORD` as `static final` constants, also used inside prompts.
  - Categories lower-cased in `main` so `Food`/`food` share one total; `Map<String, Integer> totals = new TreeMap<>()`.
  - Prints per-category totals, total income, total expenses, balance. Tested: 500 income, 20 + 300 expense →
    500 / 320 / 180. Tested -20 and 0 rejected → expenses 20, balance -20.
- Updated CLAUDE.md: Claude reads code itself; when asking for test output, say exactly what to type and to paste
  the Run window text.

**Understood**
- `equals` is case-sensitive → `equalsIgnoreCase` for the exit word; map keys are case-sensitive too, so normalize
  before storing. TreeMap sorts uppercase before lowercase.
- Design reason for lower-casing in `main`: each method does only what its name says. Good reasoning.
- Exit-first `if` + `break` reads better than `if (!...) ... else break`.
- Signed amounts are fragile (missing minus → balance off by 2× the amount); asking the type first fixes that, and
  then the amount must always be positive.
- `return scanner.nextLine()` after validation reads a *new* line; return the already-checked variable.

**Shaky, revisit next time**
- **Test before "done"**: said "done" twice without running (`readType` had the `nextLine` bug; `readAmount`).
  Once pasted an identical old output. Better than before, but still the main habit.
- **Read-back**: unused `Locale` import (from autocomplete); accidentally deleted the category print loop and
  didn't notice the output no longer matched the task. Compare output with the task line by line.
- English: "will be treat" → "treated", "forget" → "forgets", "which cause" → "causes", "2 time" → "twice".
  Typos: "readCateogry", "tranasaction", "expenes".

**My mistake this session**
- Asked "paste your code and the output" without saying what exactly to paste (same as last time). Fixed in
  CLAUDE.md. Also once added a design decision on top of a task in the same message.

**Next session: polish + ship.** Small polish (the trailing `.` in `food: 20.`, blank category input), then the
README (English practice), one review point at a time. Then Week 4: OOP.

## 2026-10-01 — Week 3, session 6 (ship): ledger polish + README

**Built / did**
- `TransactionLedger.java` polish: `readCategory` rejects blank input with `isBlank()` and asks again; categories
  are `trim().toLowerCase()`-ed in `main` (one place, consistent with his earlier design reason); removed the
  trailing `.` from category lines. All tested with pasted Run output.
- README: wrote the TransactionLedger paragraph and the Week 3 lines for `ArrayBasics`, `Average` and `Ledger`,
  revised one review point at a time. Claude wrote the remaining file lines (`ExpenseInput`, `Balance`,
  `CategoryTotals`, `TransactionLedger`) and updated "Known limitations", at his request.
- CLAUDE.md: Visal now writes only the project paragraph of the README; Claude writes the file list and limitations.

**Understood**
- Predicted the blank-category (`: 20`) and leading-space (` food` vs `food`) bugs before running. `trim()` named
  without help.
- Subject–verb agreement goes with the *real* subject: "the issue … is removed", not the nearest noun ("scores").
- Backticks are for exact code (`ArrayList`, `0`); concepts are plain English with an article ("an array",
  "a for-each loop").
- `remove(1)` removes the *second* item: index 1, counted from 0.

**Shaky, revisit next time**
- **README accuracy**: first drafts described things the code doesn't do ("list of recorded transactions",
  "removes the first expense", "total and total"). Check each claim against the code.
- **"done" without finishing**: said all `user` → `the user` was done with two still missing.
- English: verb -s after a singular subject ("combine", "pick", "finish") is still the most frequent error.
- Motivation dropped during the long README review (many small points in a row). Next time keep README review to
  2–3 points and the session shorter.

**Next session: Week 4, Monday — OOP.** Classes and objects: why an `Account` class instead of loose variables.
Week 3 is shipped once he commits and pushes.

## 2026-10-01 — Week 4, session 1 (Monday): classes and objects

**Built / did**
- `Account.java`: fields `owner` (String) and `balance` (int, BigDecimal comes later this week), plus an instance
  method `deposit(int amount)` (`balance += amount`).
- `AccountDemo.java`: two accounts made with `new`, fields set directly, printed with `printf("%s: %d%n", ...)`.
  `account2.deposit(50)` → John 10 → 60, Visal unchanged at 300. Pasted real Run output both times, unprompted
  beyond the task line. Clean code, no unused imports.

**Understood**
- `Account account3 = account1;` copies the reference; `new` ran twice, so only two objects exist. Changing
  `account3.balance` changes `account1`. Answered correctly right away.
- An instance method works on the object it was called on (`account2.deposit` → `account2`'s balance).
- `balance = -500` from outside is bad for a bank; making `balance` `private` stops it. Answered correctly.

**Shaky, revisit next time**
- Thought making `deposit` `static` would make the fields static. Corrected: it's a compile error ("non-static
  variable … static context"); the *danger* he named (one shared balance) only happens if you also make the field
  static. Quick check on Tuesday.
- Not yet covered: once `balance` is `private`, how does `main` read it or set the starting value? → constructors
  and getters.
- English: "access to every fields" → "access every field"; "make it into" → "makes it"; "In that cause" →
  "In that case"; "the field are" → "the fields are" (singular/plural agreement is still the main pattern).

**Next session (Tuesday): practice.** Make `balance` (and `owner`) `private`, see what breaks in `AccountDemo`,
then a constructor and getters. Exercises one at a time; then `withdraw` that refuses to go negative.

## 2026-10-01 — Week 4, session 2 (Tuesday): private, constructor, getters, validation

**Built / did**
- Warm-up: `static deposit` with instance fields → compile error, "static method can't access non-static balance".
  Correct; Monday's misunderstanding is fixed.
- `Account.java`: fields `private`; saw the "has private access" compile errors. Added a `public` constructor
  `Account(String owner, int balance)` with `this.owner = owner`, and `public` getters `getOwner()`/`getBalance()`.
- Validation with `throw new IllegalArgumentException(...)`:
  - constructor rejects a negative starting balance (`< 0`; `0` allowed). Tested -500 (crash) and 0 (runs).
  - `deposit` rejects `amount <= 0`. Tested -20.
  - `withdraw(int amount)`: rejects `amount <= 0`, then "Insufficient balance." if `balance - amount < 0`.
    Tested 100 (300 → 200), -50 (amount message), 1000 (insufficient).
- Pasted real Run output for every step.

**Understood**
- `private` blocks direct writes from outside, but every method that changes a field still has to validate its input.
  Found both holes (constructor, negative deposit) on his own.
- A class can't re-ask the user like `readAmount` did; it throws so the caller learns about its mistake.
- `<init>` in a stack trace means the constructor.
- `withdraw(-50)` with balance 200 → 250 ("withdraw negative adds money"). Traced it correctly when asked.

**Shaky, revisit next time**
- **Testing only the expected inputs**: first `withdraw` passed his tests but allowed negative amounts. Habit to
  build: for each method, also test 0, a negative number, and a value that's too big.
- Pasted only one of two requested outputs once (the `0` constructor case). Fine after a reminder.
- Small cleanups still open (not raised yet): `deposit`/`withdraw` repeat the same `amount <= 0` check (a private
  helper could do it); `deposit`/`withdraw` have no `public` while the getters do; constructor message says
  "Amount" for a starting balance.

**Next session (Thursday): practice + review.** Warm-up on the duplicated amount check, then `transfer(Account to,
int amount)`, then why `int`/`double` are wrong for money → `BigDecimal`.

## 2026-10-01 — Week 4, session 3 (Thursday): helper method, transfer, why BigDecimal

**Built / did**
- `Account.java` cleanup, one point at a time:
  - Duplicated `amount <= 0` check moved into `private void validateAmount(int amount)`; `deposit` and `withdraw`
    call it. Tested `withdraw(-20)` → same message, stack trace shows `withdraw` → `validateAmount`.
  - `deposit` / `withdraw` made `public`; constructor message changed from "Amount" to "Balance".
- `transfer(Account to, int amount)`: `withdraw(amount)` then `to.deposit(amount)`. Tested 50 from Visal (200 → 150)
  to John (60 → 110).
  - Self-transfer check: first version compared `to.owner.equals(owner)` (bug: blocks two different accounts with
    the same owner). Then `to.equals(this)`, then `to == this`. Tested `account1.transfer(account1, 50)` → throws
    before any money moves.
- `MoneyDemo.java`: `0.1 + 0.2` with `double` → `0.30000000000000004`; with `new BigDecimal("0.1").add(...)` → `0.3`.
  Pasted real Run output every time.

**Understood**
- A rule written twice drifts apart; one helper gives it one home. Internal helpers are `private`, actions are `public`.
- Error messages should name the value that's actually wrong.
- `withdraw` before `deposit` in `transfer`: if withdraw throws, nothing has changed (deposit first would create
  money). Answered right away.
- Predicted the bad inputs for `transfer` (0, negative → throw; too big → insufficient). Missed self-transfer until
  asked, then traced it correctly (balance unchanged, but a bank should reject it).
- `==` = same object; `equals` can be redefined by a class (String compares text), so "same account" checks use `==`.
- `double` is binary, so 0.1 isn't exact; small errors add up over many transactions. `new BigDecimal(0.1)` copies the
  `double`'s error, so create money from a `String`.

**Shaky, revisit next time**
- "Same object" vs "same data": first reached for the owner name. Quick check: two `new Account("Visal", 300)` —
  are they `==`? Are they the same account?
- Edge-case listing is better (3 of 4 cases unprompted). Keep asking "what else could go wrong?" before testing.
- Not covered yet: `null` passed as `to` (would throw NullPointerException); BigDecimal in `Account` itself
  (`compareTo` instead of `<`, `subtract`, `signum`).

**My mistake this session**
- Gave a task and its test instructions in one message again ("dude one thing at a time"). Task messages = only the
  build step and the finished result.

**Next session (Saturday): build.** Week 4 project: switch `Account` from `int` to `BigDecimal` (created from
Strings), one method at a time, then a small menu-driven bank demo (deposit / withdraw / transfer between two
accounts). Warm-up: the "same object vs same data" check above.

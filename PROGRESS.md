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

# premium-user-oop

A minimal Java project demonstrating **inheritance** — one of the core pillars of object-oriented programming. An abstract `User` class serves as the base type, and a concrete `PremiumUser` subclass extends it with a credit-based system that gates access to AI image generation.

---

## Project Structure

```
premium-user-oop/
├── pom.xml
└── src/
    ├── main/
    │   └── java/com/example/
    │       ├── User.java          # Abstract base class
    │       ├── PremiumUser.java   # Concrete subclass with credit system
    │       └── Main.java          # Entry point
    └── test/
        └── java/                  # Test files go here (JUnit 5 + jqwik)
```

---

## Classes

### `User` (abstract)

The common base type for all user kinds in the system. Declared `abstract` so it cannot be instantiated directly — it exists purely to define the inheritance hierarchy.

```java
public abstract class User { }
```

### `PremiumUser extends User`

A concrete subclass that owns a credit balance and exposes two public methods.

| Member | Type | Description |
|---|---|---|
| `credits` | `public int` | Usage budget, initialized to `100` |
| `generate_image()` | `void` *(private)* | Deducts 5 credits and prints a success message — internal use only |
| `use_ai_prompt()` | `void` | The only public entry point for image generation; guards `generate_image()` — only calls it when `credits > 5` |

**Credit guard logic:**

```
credits > 5  →  generate_image() is called  (credits decremented by 5)
credits ≤ 5  →  nothing happens
```

Calling `generate_image()` directly is no longer possible from outside the class — it is `private`. All image generation must go through `use_ai_prompt()`, which enforces the credit guard.

### `Main`

The runnable entry point. It:
1. Creates a `PremiumUser` (starts with 100 credits)
2. Calls `use_ai_prompt()` — credits drop to 95
3. Calls `use_ai_prompt()` again — credits drop to 90

---

## Requirements

- Java 17+
- Maven 3.6+

---

## Build & Run

**Compile:**
```bash
mvn compile
```

**Run the entry point:**
```bash
mvn exec:java -Dexec.mainClass="com.example.Main"
```

**Run tests:**
```bash
mvn test
```

---

## Testing

The project is set up with:

- **JUnit 5** (`junit-jupiter 5.10.2`) for unit tests
- **jqwik** (`1.8.4`) for property-based tests

Test files placed under `src/test/java/` are picked up automatically by the Surefire plugin. Files matching `*Test.java`, `*Tests.java`, or `*Properties.java` are included.

### Correctness Properties

The design defines four formal correctness properties suitable for property-based testing:

| # | Property | Validates |
|---|---|---|
| 1 | For any initial credits `c`, after one `generate_image()` call, `credits == c - 5` | Req 4.2 |
| 2 | For any `credits > 5`, `use_ai_prompt()` decrements credits by exactly 5 | Req 5.2 |
| 3 | For any `credits ≤ 5`, `use_ai_prompt()` leaves credits unchanged | Req 5.3 |
| 4 | For any initial credits `c` and call count `n` (0–20), after `n` calls to `generate_image()`, `credits == c - (5 * n)` | Req 4.2 |

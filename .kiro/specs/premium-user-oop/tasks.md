# Implementation Plan: premium-user-oop

## Overview

Implement a minimal Java OOP hierarchy demonstrating inheritance. An abstract `User` class serves as the base type; a concrete `PremiumUser` subclass extends it with a credit-based system gating AI image generation. A `Main` entry point wires everything together. Tests are written with JUnit 5 for unit tests and jqwik for property-based tests.

## Tasks

- [x] 1. Set up project structure and build configuration
  - Create a Maven `pom.xml` with JUnit 5 (`junit-jupiter 5.10.2`) and jqwik (`1.8.4`) test dependencies
  - Create the standard Maven source layout: `src/main/java/` and `src/test/java/`
  - Verify the project compiles with `mvn compile`
  - _Requirements: 1, 2_

- [x] 2. Implement the `User` abstract class
  - [x] 2.1 Create `User.java`
    - Declare `User` as an `abstract` class (not `final`)
    - No fields or methods required at this stage
    - _Requirements: 1.1, 1.2_

- [x] 3. Implement the `PremiumUser` class
  - [x] 3.1 Create `PremiumUser.java` extending `User`
    - Declare `PremiumUser` as a concrete (non-abstract) class that `extends User`
    - Declare `public int credits` initialized to `100`
    - _Requirements: 2.1, 2.2, 3.1, 3.2_

  - [x] 3.2 Implement `generate_image()`
    - Subtract `5` from `credits`
    - Print a success message to standard output after the deduction
    - _Requirements: 4.1, 4.2, 4.3_

  - [ ]* 3.3 Write unit tests for `generate_image()`
    - `testInitialCredits` — new `PremiumUser` has `credits == 100`
    - `testGenerateImageDecrementsCredits` — after one call, `credits == 95`
    - `testGenerateImagePrintsSuccess` — stdout contains a non-empty success message
    - _Requirements: 3.2, 4.2, 4.3_

  - [ ]* 3.4 Write property test for `generate_image()` — Property 1
    - **Property 1: Credit deduction on image generation**
    - For any initial credit value `c`, after one `generate_image()` call `credits == c - 5`
    - Tag comment: `// Feature: premium-user-oop, Property 1: Credit deduction on image generation`
    - **Validates: Requirements 4.2**

  - [ ]* 3.5 Write property test for additive credit deduction — Property 4
    - **Property 4: Credit deduction is additive across multiple calls**
    - For any initial credits `c` and call count `n` (0–20), after `n` calls `credits == c - (5 * n)`
    - Tag comment: `// Feature: premium-user-oop, Property 4: Credit deduction is additive across multiple calls`
    - **Validates: Requirements 4.2**

  - [x] 3.6 Implement `use_ai_prompt()`
    - If `credits > 5`, call `generate_image()`; otherwise do nothing
    - _Requirements: 5.1, 5.2, 5.3_

  - [ ]* 3.7 Write unit tests for `use_ai_prompt()`
    - `testUseAiPromptCallsGenerateWhenSufficient` — with `credits > 5`, credits decremented by 5
    - `testUseAiPromptBlocksWhenCreditsExactly5` — with `credits == 5`, credits unchanged
    - `testUseAiPromptBlocksWhenCreditsZero` — with `credits == 0`, credits unchanged
    - _Requirements: 5.2, 5.3_

  - [ ]* 3.8 Write property test for `use_ai_prompt()` delegation — Property 2
    - **Property 2: use_ai_prompt delegates when credits > 5**
    - For any `credits > 5`, calling `use_ai_prompt()` decrements credits by exactly 5
    - Tag comment: `// Feature: premium-user-oop, Property 2: use_ai_prompt delegates when credits > 5`
    - **Validates: Requirements 5.2**

  - [ ]* 3.9 Write property test for `use_ai_prompt()` guard — Property 3
    - **Property 3: Guard blocks generation when credits ≤ 5**
    - For any `credits ≤ 5`, calling `use_ai_prompt()` leaves credits unchanged
    - Tag comment: `// Feature: premium-user-oop, Property 3: Guard blocks generation when credits ≤ 5`
    - **Validates: Requirements 5.3**

- [x] 4. Checkpoint — Ensure all tests pass
  - Run `mvn test` and confirm all unit and property tests pass; ask the user if questions arise.

- [x] 5. Implement the `Main` entry point
  - [x] 5.1 Create `Main.java` with `public static void main(String[] args)`
    - Instantiate a `PremiumUser` object
    - Call `use_ai_prompt()` on the instance
    - Call `generate_image()` directly on the same instance
    - _Requirements: 6.1, 6.2, 6.3_

  - [ ]* 5.2 Write unit test for `Main`
    - `testMainRunsWithoutException` — `Main.main(new String[]{})` executes without throwing
    - _Requirements: 6_

- [x] 6. Final checkpoint — Ensure all tests pass
  - Run `mvn test` and confirm the full test suite is green; ask the user if questions arise.

## Notes

- Tasks marked with `*` are optional and can be skipped for a faster MVP
- Each task references specific requirements for traceability
- Property tests use jqwik with a minimum of 100 iterations per property
- Properties 1 and 4 overlap intentionally: Property 1 is a focused single-call regression; Property 4 validates the additive invariant across `n` calls
- No external services, persistence, or frameworks are required — plain Java only

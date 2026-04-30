# Requirements Document

## Introduction

This feature implements a Java OOP hierarchy using the inheritance pillar. An abstract `User` class serves as the base type, and a `PremiumUser` class extends it with a credit-based system that controls access to AI image generation. A `PremiumUser` instance starts with 100 credits; each image generation costs 5 credits. The `use_ai_prompt()` method acts as a guard that only triggers generation when sufficient credits are available.

## Glossary

- **User**: The abstract base class that defines the common contract for all user types in the system.
- **PremiumUser**: A concrete subclass of `User` that holds a credit balance and exposes AI-powered capabilities.
- **Credits**: An integer value owned by `PremiumUser` representing the remaining usage budget. Initialized to 100.
- **generate_image()**: A method on `PremiumUser` that consumes 5 credits and produces a success message.
- **use_ai_prompt()**: A method on `PremiumUser` that validates credit availability before delegating to `generate_image()`.

---

## Requirements

### Requirement 1: Abstract User Base Class

**User Story:** As a developer, I want an abstract `User` class, so that all user types share a common base type and the inheritance hierarchy is well-defined.

#### Acceptance Criteria

1. THE `User` class SHALL be declared as an abstract Java class.
2. THE `User` class SHALL be extendable by concrete subclasses (i.e., it must not be declared `final`).

---

### Requirement 2: PremiumUser Inherits from User

**User Story:** As a developer, I want `PremiumUser` to extend `User`, so that it participates in the inheritance hierarchy and can be used polymorphically wherever a `User` is expected.

#### Acceptance Criteria

1. THE `PremiumUser` class SHALL extend the `User` abstract class.
2. THE `PremiumUser` class SHALL be a concrete (non-abstract) Java class.

---

### Requirement 3: Credit Balance Initialization

**User Story:** As a developer, I want `PremiumUser` to start with 100 credits, so that a newly created instance has a well-defined, non-zero usage budget.

#### Acceptance Criteria

1. THE `PremiumUser` class SHALL declare an `int` instance variable named `credits`.
2. WHEN a `PremiumUser` instance is created, THE `PremiumUser` SHALL initialize `credits` to `100`.

---

### Requirement 4: Image Generation Method

**User Story:** As a developer, I want a `generate_image()` method on `PremiumUser`, so that the system can consume credits and confirm successful image generation.

#### Acceptance Criteria

1. THE `PremiumUser` class SHALL declare a method named `generate_image()` with no parameters and a `void` return type.
2. WHEN `generate_image()` is called, THE `PremiumUser` SHALL subtract `5` from the `credits` variable.
3. WHEN `generate_image()` is called, THE `PremiumUser` SHALL print a success message to standard output after subtracting the credits.

---

### Requirement 5: AI Prompt Guard Method

**User Story:** As a developer, I want a `use_ai_prompt()` method that checks credit availability before generating an image, so that `generate_image()` is never called when credits are insufficient.

#### Acceptance Criteria

1. THE `PremiumUser` class SHALL declare a method named `use_ai_prompt()` with no parameters and a `void` return type.
2. WHEN `use_ai_prompt()` is called and `credits` is greater than `5`, THE `PremiumUser` SHALL call `generate_image()`.
3. IF `credits` is not greater than `5` when `use_ai_prompt()` is called, THEN THE `PremiumUser` SHALL NOT call `generate_image()`.

---

### Requirement 6: Runtime Demonstration

**User Story:** As a developer, I want a runnable entry point that instantiates `PremiumUser` and exercises both methods, so that the feature can be verified end-to-end.

#### Acceptance Criteria

1. THE application SHALL create an instance of `PremiumUser`.
2. WHEN the application runs, THE application SHALL call `use_ai_prompt()` on the `PremiumUser` instance.
3. WHEN `use_ai_prompt()` has returned, THE application SHALL call `generate_image()` directly on the same `PremiumUser` instance.

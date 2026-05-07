# LFAF
# Lab 4 – Regular Expressions

**Course:** Formal Languages & Finite Automata  
**Author:** Mihai Zastinceanu  
**Variant:** 1

---

# Theory

A **regular expression (regex)** is a sequence of symbols used to describe patterns inside strings.  
Regular expressions are widely used in compiler construction, lexical analysis, input validation, searching algorithms, and text processing.

A regex defines a formal language by specifying which strings are valid according to a set of rules.

Main regex operators:

- `|` → OR operator
- `*` → zero or more repetitions
- `+` → one or more repetitions
- `?` → optional symbol
- `{n}` → exact number of repetitions
- `()` → grouping expressions

Regular expressions are strongly connected to finite automata because every regular expression can be transformed into an equivalent automaton.

---

# Objectives

- Understand what regular expressions are;
- Learn how regex operators work;
- Dynamically interpret regular expressions;
- Generate valid strings based on regex rules;
- Avoid hardcoded generation;
- Implement recursive parsing;
- Build a syntax tree representation of regex expressions;
- Demonstrate regex processing step-by-step.

---

# Implemented Regular Expressions

The following regex expressions were implemented for Variant 1:

```text
(a|b)(c|d)E+G?
P(Q|R|S)T(UV|W|X)*Z+
1(0|1)*2((3|4){5})36
```

Explanation:

- `(a|b)` → choose between a and b
- `E+` → repeat E one or more times
- `G?` → optional G
- `(UV|W|X)*` → repeat group between 0 and 5 times
- `((3|4){5})` → exactly 5 repetitions of 3 or 4

---

# Project Structure

```text
src/
 ├── Main.java
 ├── RegexGenerator.java
 ├── RegexParser.java
 ├── Node.java
 ├── LiteralNode.java
 ├── ConcatNode.java
 ├── OrNode.java
 ├── StarNode.java
 ├── PlusNode.java
 └── OptionalNode.java
```

---

# Implementation Description

## Main Class

The `Main` class contains the regex expressions and demonstrates the generation of valid strings.

It:
- stores regex expressions;
- calls the generator;
- prints generated strings;
- demonstrates dynamic parsing.

---

## RegexParser Class

The `RegexParser` class dynamically interprets the regular expression using recursive descent parsing.

Responsibilities:
- parse symbols;
- detect operators;
- process grouped expressions;
- build syntax trees dynamically.

Supported operators:
- `|`
- `*`
- `+`
- `?`
- `{n}`

---

## RegexGenerator Class

The `RegexGenerator` class generates valid strings by traversing the syntax tree recursively.

Generation is NOT hardcoded.

The generator dynamically interprets:
- alternation;
- repetition;
- optional symbols;
- concatenation.

---

## Node Classes

The project uses object-oriented programming and syntax tree nodes.

### LiteralNode
Stores simple symbols.

### ConcatNode
Combines multiple nodes sequentially.

### OrNode
Randomly selects one valid option.

### StarNode
Repeats an expression between 0 and 5 times.

### PlusNode
Repeats an expression between 1 and 5 times.

### OptionalNode
Randomly includes or excludes an expression.

---

# Example Output

```text
acEEEEG
bdEEE
bcEG

PQTUVUVZ
PRTWZ
PSTWWXXUVZZZZZ

1024344336
11111024333336
10101123444436
```

Because random generation is used, output changes every execution.

---

# Bonus Requirement

The project also implements the bonus requirement by showing the sequence of regex processing.

Example:

```text
Processing symbol: E
Applying operator: +
```

This demonstrates how the parser interprets the expression step-by-step.

---

# Code Example

```java
public String generate(String regex) {

    Node root = parser.parse(regex);

    return root.generate();
}
```

The parser creates a syntax tree and recursively generates valid strings.

---

# Faced Difficulties

Several implementation difficulties appeared during development:

- understanding recursive descent parsing;
- handling grouped expressions;
- dynamically processing operators;
- avoiding hardcoded solutions;
- limiting infinite repetitions from `*`;
- implementing syntax trees correctly.

These problems were solved using recursive algorithms and object-oriented programming.

---

# Conclusions

This laboratory work successfully demonstrates dynamic regular expression interpretation and valid string generation.

The implementation:
- dynamically parses regex expressions;
- constructs syntax trees;
- recursively generates valid strings;
- supports multiple regex operators;
- avoids hardcoded generation logic.

The project improved understanding of:
- formal languages;
- regular expressions;
- recursive parsing;
- syntax trees;
- finite automata concepts;
- object-oriented design.

The generated strings fully respect the rules defined by the regular expressions.

---

# How To Run

1. Open the project in IntelliJ IDEA;
2. Build the project;
3. Run `Main.java`;
4. Observe generated valid strings.

---

# GitHub Repository

The project is uploaded as a public GitHub repository according to laboratory requirements.
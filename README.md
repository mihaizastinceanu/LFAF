# Lexer & Scanner Implementation

**Course:** Formal Languages & Finite Automata  
**Author:** Mihai Zastinceanu  

---

## Overview

Lexical analysis is the first stage of processing in a compiler or interpreter. It consists of transforming a sequence of characters into a sequence of meaningful elements called tokens.

A lexer (also called scanner or tokenizer) reads the input and groups characters into lexemes, then assigns each lexeme a token type. These tokens are later used by the parser for syntactic analysis.

In this laboratory work, a custom lexer was implemented in Java to tokenize a simple mathematical expression language that includes identifiers, numbers, operators, and trigonometric functions.

---

## Objectives

- Understand the concept of lexical analysis  
- Learn how a lexer/tokenizer works internally  
- Implement a custom lexer in Java  
- Tokenize expressions with integers, floats, and functions  
- Demonstrate the lexer functionality through execution  

---

## Theory

Lexical analysis converts raw input text into tokens. A token is composed of:
- a **type** (e.g., IDENTIFIER, NUMBER, OPERATOR)
- a **value** (the actual lexeme)

The lexer processes the input character by character using rules such as:
- grouping digits into numbers
- grouping letters into identifiers
- recognizing reserved keywords (e.g., `sin`, `cos`)
- identifying operators and symbols

Whitespace is ignored, and invalid characters generate errors.

The lexer acts similarly to a finite-state machine, transitioning between states depending on the current input character.

---

## Implementation Description

The project is implemented using multiple classes:

- **TokenType.java**  
  Defines all possible token types such as identifiers, numbers, operators, and functions.

- **Token.java**  
  Represents a token with a type and a value.

- **Lexer.java**  
  The core component that reads the input string character by character and generates tokens using helper methods for numbers, identifiers, and operators.

- **Main.java**  
  The entry point that provides input, runs the lexer, and displays the resulting tokens.

The lexer supports:
- integers and floating-point numbers  
- identifiers  
- arithmetic operators  
- parentheses  
- trigonometric functions (`sin`, `cos`)  

---

## Code Snippets

```java
public static void main(String[] args) {

    String input = "x = sin(3.14) + cos(0) * 10";

    Lexer lexer = new Lexer(input);
    List<Token> tokens = lexer.tokenize();

    System.out.println("Input:");
    System.out.println(input);

    System.out.println("\nTokens:");
    for (Token t : tokens) {
        System.out.println(t);
    }
}
```
## Results
```java
Input:
x = sin(3.14) + cos(0) * 10

Tokens:
(IDENTIFIER, "x")
(ASSIGN, "=")
(SIN, "sin")
(LPAREN, "(")
(FLOAT, "3.14")
(RPAREN, ")")
(PLUS, "+")
(COS, "cos")
(LPAREN, "(")
(INTEGER, "0")
(RPAREN, ")")
(MULTIPLY, "*")
(INTEGER, "10")
(EOF, "")
```
## Conclusion

In this laboratory work, a formal grammar and its equivalent finite automaton were successfully implemented in Java.
The Grammar class encodes Variant 25's production rules and generates valid strings through random leftmost derivation,
replacing non-terminal symbols until only terminals remain. The toFiniteAutomaton() method converts the grammar into
an NFA by mapping each non-terminal to a state and each production rule to a transition, with single-terminal
productions leading to a synthetic FINAL accepting state. The FiniteAutomaton class validates strings using subset
construction, tracking all active states across each input character.

The implementation demonstrates how regular grammars and finite automata are mathematically equivalent and
interchangeable. It also shows how production rules can be directly translated into state transitions without
loss of expressive power.

Overall, this laboratory work provided a clear understanding of the relationship between formal grammars and
automata theory. The results confirm that the finite automaton correctly accepts strings belonging to the language
(db, dab, daab, bdab, bdcba) and rejects those that do not (abc, dd), validating the correctness of both
the grammar encoding and the automaton conversion.

# Parser & Abstract Syntax Tree

## Author

Mihai Zastinceanu

Course: Formal Languages & Finite Automata

---

# Overview

This project demonstrates:

- Lexical Analysis
- Parsing
- Abstract Syntax Tree generation

The lexer uses regular expressions to identify tokens.

The parser analyzes the syntax and creates an AST.

---

# Project Structure

```text
LFAF/
├── src/
│    ├── Main.java
│    ├── TokenType.java
│    ├── Token.java
│    ├── Lexer.java
│    ├── ASTNode.java
│    ├── Parser.java
│    └── PrintTree.java
│
└── README.md
```

---

# Features

- TokenType enum
- Regular expressions for token recognition
- Recursive descent parser
- Abstract Syntax Tree
- Console visualization of AST

---

# Example Input

```text
3 + 5 * (2 - 1)
```

---

# Example Output

```text
INPUT:
3 + 5 * (2 - 1)

TOKENS:
Token{type=NUMBER, value='3'}
Token{type=PLUS, value='+'}
Token{type=NUMBER, value='5'}
Token{type=MULTIPLY, value='*'}
Token{type=LEFT_PAREN, value='('}
Token{type=NUMBER, value='2'}
Token{type=MINUS, value='-'}
Token{type=NUMBER, value='1'}
Token{type=RIGHT_PAREN, value=')'}
Token{type=EOF, value=''}

ABSTRACT SYNTAX TREE:
+
  3
  *
    5
    -
      2
      1
```

---

# How It Works

## Lexer

The lexer converts the input string into tokens using regular expressions.

## Parser

The parser uses recursive descent parsing and respects operator precedence.

## AST

The AST represents the hierarchical structure of the arithmetic expression.

Operators become parent nodes.

Numbers become leaf nodes.

---

# Technologies

- Java
- IntelliJ IDEA

---

# References

- https://en.wikipedia.org/wiki/Parsing
- https://en.wikipedia.org/wiki/Abstract_syntax_tree
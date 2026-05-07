# LFAF
# Lab 5 – Chomsky Normal Form

**Course:** Formal Languages & Finite Automata  
**Author:** Mihai Zastinceanu  
**Variant:** 25

---

# Theory

A **Context-Free Grammar (CFG)** is a formal grammar where every production rule has a single non-terminal symbol on the left-hand side.

A grammar is in **Chomsky Normal Form (CNF)** if every production has one of the following forms:

```text
A → BC
```

or

```text
A → a
```

Where:
- `A, B, C` are non-terminal symbols;
- `a` is a terminal symbol.

Chomsky Normal Form simplifies parsing algorithms and is widely used in compiler construction and automata theory.

---

# Objectives

- Learn about Chomsky Normal Form;
- Understand grammar normalization;
- Eliminate ε-productions;
- Eliminate renaming productions;
- Eliminate inaccessible symbols;
- Eliminate non-productive symbols;
- Convert the grammar into CNF;
- Understand formal grammar transformations.

---

# Initial Grammar

```text
G = (VN, VT, P, S)

VN = {S, A, B, C, D}
VT = {a, b}

P = {
1. S → bA
2. S → BC
3. A → a
4. A → aS
5. A → bCaCa
6. B → A
7. B → bS
8. B → bCAa
9. C → ε
10. C → AB
11. D → AB
}
```

---

# Step 1 – Elimination of ε-Productions

Nullable symbol:

```text
C → ε
```

Because `C` is nullable, all productions containing `C` must generate additional productions without `C`.

Result:

```text
P1 = {
S → bA
S → BC
S → B

A → a
A → aS
A → bCaCa
A → baCa
A → bCaa
A → baa

B → A
B → bS
B → bCAa
B → bAa

C → AB
D → AB
}
```

---

# Step 2 – Elimination of Renaming Productions

Renaming production:

```text
B → A
S → B
```

Substitute productions of `A` and `B`.

Result:

```text
P2 = {
S → bA
S → BC
S → a
S → aS
S → bCaCa
S → baCa
S → bCaa
S → baa
S → bS
S → bCAa
S → bAa

A → a
A → aS
A → bCaCa
A → baCa
A → bCaa
A → baa

B → bS
B → bCAa
B → bAa
B → a
B → aS
B → bCaCa
B → baCa
B → bCaa
B → baa

C → AB
D → AB
}
```

---

# Step 3 – Elimination of Non-Productive Symbols

Productive symbols are symbols that can derive terminal strings.

Productive symbols:

```text
{S, A, B, C, D}
```

No non-productive symbols exist.

Grammar remains unchanged.

---

# Step 4 – Elimination of Inaccessible Symbols

Accessible symbols starting from `S`:

```text
{S, A, B, C}
```

Symbol `D` is inaccessible because it cannot be reached from `S`.

Remove:

```text
D → AB
```

Result:

```text
P4 = {
S → bA
S → BC
S → a
S → aS
S → bCaCa
S → baCa
S → bCaa
S → baa
S → bS
S → bCAa
S → bAa

A → a
A → aS
A → bCaCa
A → baCa
A → bCaa
A → baa

B → bS
B → bCAa
B → bAa
B → a
B → aS
B → bCaCa
B → baCa
B → bCaa
B → baa

C → AB
}
```

---

# Step 5 – Conversion to Chomsky Normal Form

Introduce new variables for terminals:

```text
X1 → a
X2 → b
```

Break productions longer than 2 symbols into binary productions.

Final CNF Grammar:

```text
VN = {S, A, B, C, X1, X2, Y1, Y2, Y3}

VT = {a, b}
```

Productions:

```text
S → X2A
S → BC
S → a
S → X1S
S → X2Y1
S → X2Y2
S → X2Y3
S → X2S

A → a
A → X1S
A → X2Y1
A → X2Y2
A → X2Y3

B → X2S
B → X2Y2
B → X2Y3
B → a
B → X1S
B → X2Y1

C → AB

Y1 → CY2
Y2 → X1C
Y3 → X1X1

X1 → a
X2 → b
```

---

# Implementation

The laboratory work was implemented manually by applying all CNF normalization rules step-by-step.

Operations performed:
- ε-production elimination;
- renaming elimination;
- inaccessible symbol elimination;
- non-productive symbol elimination;
- binary decomposition of productions.

---

# Conclusions

This laboratory work demonstrated the complete process of converting a context-free grammar into Chomsky Normal Form.

The transformation process included:
- eliminating ε-productions;
- removing renaming productions;
- eliminating inaccessible symbols;
- removing useless productions;
- transforming productions into binary form.

The final grammar satisfies all CNF requirements:
- every production is either `A → BC`
- or `A → a`

The laboratory improved understanding of:
- context-free grammars;
- grammar simplification;
- formal language normalization;
- recursive grammar structures;
- automata theory concepts.

---

# References

- Chomsky Normal Form:
  https://en.wikipedia.org/wiki/Chomsky_normal_form
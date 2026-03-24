# Determinism in Finite Automata. Conversion from NDFA to DFA. Chomsky Hierarchy

**Course:** Formal Languages & Finite Automata  
**Author:** Mihai Zastinceanu  

---

## Overview

A finite automaton is a mathematical model used to represent computational processes. It consists of a finite set of states, a start state, transitions between states, and a set of final states.

Automata can be deterministic or non-deterministic. In a deterministic finite automaton (DFA), each state has exactly one transition for each input symbol. In contrast, a non-deterministic finite automaton (NDFA) may have multiple possible transitions for the same input symbol.

This laboratory work focuses on understanding non-determinism and converting an NDFA into an equivalent DFA, as well as exploring the relationship between automata and grammars using the Chomsky hierarchy.

---

## Objectives

- Understand the concept of finite automata and their applications  
- Determine whether a finite automaton is deterministic or non-deterministic  
- Convert a finite automaton into a regular grammar  
- Classify the grammar according to the Chomsky hierarchy  
- Implement NDFA to DFA conversion using subset construction  

---

## Theory

A **finite automaton (FA)** is defined as a 5-tuple (Q, Σ, δ, q0, F), where:
- Q is the set of states  
- Σ is the alphabet  
- δ is the transition function  
- q0 is the initial state  
- F is the set of final states  

A **deterministic finite automaton (DFA)** has exactly one transition for each symbol from every state. A **non-deterministic finite automaton (NDFA)** can have multiple transitions for the same input.

The **subset construction algorithm** is used to convert an NDFA into a DFA by creating new states that represent sets of NDFA states.

The **Chomsky hierarchy** classifies grammars into:
- Type 3: Regular grammars  
- Type 2: Context-free grammars  
- Type 1: Context-sensitive grammars  
- Type 0: Unrestricted grammars  

Regular grammars correspond directly to finite automata.

---

## Implementation Description

The project is implemented in Java and consists of multiple classes:

- **FiniteAutomaton class**  
  Handles the definition of the automaton, including states, transitions, and final states. It also determines whether the automaton is deterministic.

- **Grammar class**  
  Represents the grammar derived from the automaton and includes functionality to classify it according to the Chomsky hierarchy.

- **Conversion methods**  
  Implement the transformation of a finite automaton into a regular grammar and the conversion of NDFA to DFA using subset construction.

- **Main class**  
  Acts as the client program, initializing the automaton, running conversions, and displaying results.

---

## Code Snippets

```java
public static void main(String[] args) {

    FiniteAutomaton fa = new FiniteAutomaton();

    System.out.println("Is DFA? " + fa.isDeterministic());

    Grammar grammar = fa.toGrammar();

    System.out.println("\nGenerated Grammar:");
    grammar.printProductions();

    System.out.println("\nGrammar Type:");
    System.out.println(grammar.classifyGrammar());

    System.out.println("\nNDFA → DFA Conversion:");
    fa.convertToDFA();
}
```

## Conclusion

In this laboratory work, the concepts of determinism in finite automata and the Chomsky hierarchy were successfully explored and implemented.

First, the given finite automaton was analyzed and it was correctly determined that it is a non-deterministic finite automaton (NDFA), since from state q0 with input 'a' multiple transitions are possible.

Next, the conversion from NDFA to DFA was implemented using the subset construction method. The resulting DFA states were represented as combinations of NDFA states, demonstrating how non-determinism can be eliminated while preserving the language.

Additionally, the finite automaton was converted into an equivalent regular grammar. Based on the structure of the productions (A → aB and A → a), the grammar was correctly classified as Type 3 (Regular Grammar) according to the Chomsky hierarchy.

Overall, this laboratory work provided a practical understanding of:
- the difference between deterministic and non-deterministic automata,
- the process of converting NDFA to DFA,
- and the relationship between finite automata and regular grammars.

The implementation confirms that theoretical concepts from formal languages can be effectively applied in practice.

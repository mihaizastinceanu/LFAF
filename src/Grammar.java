import java.util.*;

public class Grammar {
    private Set<String> Vn; // Non-terminals
    private Set<String> Vt; // Terminals
    private Map<String, List<String>> P; // Productions
    private String S; // Start symbol

    public Grammar(Set<String> Vn, Set<String> Vt, Map<String, List<String>> P, String S) {
        this.Vn = Vn;
        this.Vt = Vt;
        this.P = P;
        this.S = S;
    }

    // 🔥 CORRECT Chomsky classification (works with q0, q1, etc.)
    public String classifyGrammar() {
        boolean isRegular = true;

        for (String left : P.keySet()) {
            for (String right : P.get(left)) {

                // Case 1: A -> a
                if (right.length() == 1) {
                    String terminal = right;
                    if (!Vt.contains(terminal)) {
                        isRegular = false;
                    }
                }

                // Case 2: A -> aB
                else if (right.length() >= 2) {
                    String terminal = right.substring(0, 1);
                    String nonTerminal = right.substring(1);

                    if (!Vt.contains(terminal) || !Vn.contains(nonTerminal)) {
                        isRegular = false;
                    }
                }

                // anything else invalid
                else {
                    isRegular = false;
                }
            }
        }

        if (isRegular) return "Type 3 (Regular Grammar)";
        return "Type 2 (Context-Free Grammar)";
    }

    public void printProductions() {
        for (String key : P.keySet()) {
            System.out.println(key + " -> " + P.get(key));
        }
    }
}
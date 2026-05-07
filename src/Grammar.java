import java.util.*;

public class Grammar {

    private Set<String> Vn; // Non-terminals
    private Set<String> Vt; // Terminals
    private Map<String, List<String>> P; // Productions
    private String S; // Start symbol

    // Default constructor
    public Grammar() {

        Vn = new HashSet<>(Arrays.asList("S", "A", "B"));
        Vt = new HashSet<>(Arrays.asList("a", "b", "d"));

        P = new HashMap<>();

        P.put("S", Arrays.asList("dA", "bB"));
        P.put("A", Arrays.asList("aA", "b"));
        P.put("B", Arrays.asList("dB", "a"));

        S = "S";
    }

    // Constructor with parameters
    public Grammar(Set<String> Vn, Set<String> Vt,
                   Map<String, List<String>> P, String S) {

        this.Vn = Vn;
        this.Vt = Vt;
        this.P = P;
        this.S = S;
    }

    // Generate random string
    public String generateString() {

        Random random = new Random();

        String current = S;

        while (true) {

            boolean replaced = false;

            for (String vn : Vn) {

                int index = current.indexOf(vn);

                if (index != -1) {

                    List<String> productions = P.get(vn);

                    String production =
                            productions.get(random.nextInt(productions.size()));

                    current =
                            current.substring(0, index)
                                    + production
                                    + current.substring(index + vn.length());

                    replaced = true;
                    break;
                }
            }

            if (!replaced)
                break;
        }

        return current;
    }

    // Convert Grammar to Finite Automaton
    public FiniteAutomaton toFiniteAutomaton() {

        Set<String> Q = new HashSet<>(Vn);
        Q.add("F");

        Set<String> Sigma = new HashSet<>(Vt);

        Map<String, Map<String, List<String>>> delta = new HashMap<>();

        for (String left : P.keySet()) {

            delta.putIfAbsent(left, new HashMap<>());

            for (String right : P.get(left)) {

                // Case: A -> a
                if (right.length() == 1) {

                    String terminal = right;

                    delta.get(left)
                            .putIfAbsent(terminal, new ArrayList<>());

                    delta.get(left)
                            .get(terminal)
                            .add("F");
                }

                // Case: A -> aB
                else {

                    String terminal = right.substring(0, 1);
                    String nextState = right.substring(1);

                    delta.get(left)
                            .putIfAbsent(terminal, new ArrayList<>());

                    delta.get(left)
                            .get(terminal)
                            .add(nextState);
                }
            }
        }

        Set<String> F = new HashSet<>();
        F.add("F");

        return new FiniteAutomaton(Q, Sigma, delta, S, F);
    }

    // Chomsky classification
    public String classifyGrammar() {

        boolean isRegular = true;

        for (String left : P.keySet()) {

            for (String right : P.get(left)) {

                // A -> a
                if (right.length() == 1) {

                    if (!Vt.contains(right)) {
                        isRegular = false;
                    }
                }

                // A -> aB
                else {

                    String terminal = right.substring(0, 1);
                    String nonTerminal = right.substring(1);

                    if (!Vt.contains(terminal)
                            || !Vn.contains(nonTerminal)) {

                        isRegular = false;
                    }
                }
            }
        }

        if (isRegular)
            return "Type 3 (Regular Grammar)";

        return "Type 2 (Context-Free Grammar)";
    }

    public void printProductions() {

        for (String key : P.keySet()) {
            System.out.println(key + " -> " + P.get(key));
        }
    }
}
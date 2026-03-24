import java.util.*;

public class FiniteAutomaton {
    public Set<String> Q;
    public Set<String> Sigma;
    public Map<String, Map<String, List<String>>> delta;
    public String q0;
    public Set<String> F;

    public FiniteAutomaton() {
        Q = new HashSet<>(Arrays.asList("q0", "q1", "q2", "q3"));
        Sigma = new HashSet<>(Arrays.asList("a", "b"));
        q0 = "q0";
        F = new HashSet<>(Arrays.asList("q2"));

        delta = new HashMap<>();

        addTransition("q0", "a", "q0");
        addTransition("q0", "a", "q1");
        addTransition("q1", "a", "q2");
        addTransition("q1", "b", "q1");
        addTransition("q2", "a", "q3");
        addTransition("q3", "a", "q1");
    }

    public void addTransition(String from, String symbol, String to) {
        delta.putIfAbsent(from, new HashMap<>());
        delta.get(from).putIfAbsent(symbol, new ArrayList<>());
        delta.get(from).get(symbol).add(to);
    }

    // 🔍 Check determinism
    public boolean isDeterministic() {
        for (String state : delta.keySet()) {
            for (String symbol : delta.get(state).keySet()) {
                if (delta.get(state).get(symbol).size() > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    // 🔄 Convert FA → Grammar
    public Grammar toGrammar() {
        Map<String, List<String>> P = new HashMap<>();

        for (String state : delta.keySet()) {
            for (String symbol : delta.get(state).keySet()) {
                for (String next : delta.get(state).get(symbol)) {

                    P.putIfAbsent(state, new ArrayList<>());

                    // transition rule
                    P.get(state).add(symbol + next);

                    // if next is final → terminal rule
                    if (F.contains(next)) {
                        P.get(state).add(symbol);
                    }
                }
            }
        }

        return new Grammar(Q, Sigma, P, q0);
    }
}
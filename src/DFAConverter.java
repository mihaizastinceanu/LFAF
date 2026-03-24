import java.util.*;

public class DFAConverter {

    public static void convertToDFA(FiniteAutomaton nfa) {
        Set<Set<String>> visited = new HashSet<>();
        Queue<Set<String>> queue = new LinkedList<>();

        Set<String> start = new HashSet<>();
        start.add(nfa.q0);

        queue.add(start);
        visited.add(start);

        System.out.println("DFA States and Transitions:");

        while (!queue.isEmpty()) {
            Set<String> current = queue.poll();

            for (String symbol : nfa.Sigma) {
                Set<String> next = new HashSet<>();

                for (String state : current) {
                    if (nfa.delta.containsKey(state) &&
                            nfa.delta.get(state).containsKey(symbol)) {

                        next.addAll(nfa.delta.get(state).get(symbol));
                    }
                }

                if (!next.isEmpty()) {
                    System.out.println(current + " --" + symbol + "--> " + next);

                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }
        }
    }
}
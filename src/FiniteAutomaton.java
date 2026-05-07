//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FiniteAutomaton {
    private Set<String> Q;
    private Set<String> Sigma;
    private Map<String, Map<String, List<String>>> delta;
    private String q0;
    private Set<String> F;

    public FiniteAutomaton(Set<String> Q, Set<String> Sigma, Map<String, Map<String, List<String>>> delta, String q0, Set<String> F) {
        this.Q = Q;
        this.Sigma = Sigma;
        this.delta = delta;
        this.q0 = q0;
        this.F = F;
    }

    public boolean stringBelongToLanguage(String input) {
        Set<String> current = new HashSet();
        current.add(this.q0);

        for(char c : input.toCharArray()) {
            Set<String> next = new HashSet();

            for(String state : current) {
                if (this.delta.containsKey(state) && ((Map)this.delta.get(state)).containsKey(String.valueOf(c))) {
                    next.addAll((Collection)((Map)this.delta.get(state)).get(String.valueOf(c)));
                }
            }

            current = next;
        }

        for(String state : current) {
            if (this.F.contains(state)) {
                return true;
            }
        }

        return false;
    }
}

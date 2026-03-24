//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Grammar {
    private Set<String> Vn = new HashSet(Arrays.asList("S", "A", "B"));
    private Set<String> Vt = new HashSet(Arrays.asList("a", "b", "c", "d"));
    private Map<String, List<String>> P = new HashMap();
    private String S = "S";
    private Random rand = new Random();

    public Grammar() {
        this.P.put("S", Arrays.asList("bS", "dA"));
        this.P.put("A", Arrays.asList("aA", "dB", "b"));
        this.P.put("B", Arrays.asList("cB", "a"));
    }

    public String generateString() {
        String current = this.S;

        boolean replaced;
        do {
            replaced = false;

            for(int i = 0; i < current.length(); ++i) {
                String symbol = String.valueOf(current.charAt(i));
                if (this.Vn.contains(symbol)) {
                    List<String> rules = (List)this.P.get(symbol);
                    String chosen = (String)rules.get(this.rand.nextInt(rules.size()));
                    current = current.substring(0, i) + chosen + current.substring(i + 1);
                    replaced = true;
                    break;
                }
            }
        } while(replaced);

        return current;
    }

    public FiniteAutomaton toFiniteAutomaton() {
        Set<String> Q = new HashSet(this.Vn);
        Q.add("FINAL");
        Set<String> Sigma = new HashSet(this.Vt);
        Map<String, Map<String, List<String>>> delta = new HashMap();
        Set<String> F = new HashSet();
        F.add("FINAL");

        for(String left : this.P.keySet()) {
            for(String right : (List)this.P.get(left)) {
                String terminal = String.valueOf(right.charAt(0));
                String next;
                if (right.length() == 1) {
                    next = "FINAL";
                } else {
                    next = String.valueOf(right.charAt(1));
                }

                delta.putIfAbsent(left, new HashMap());
                ((Map)delta.get(left)).putIfAbsent(terminal, new ArrayList());
                ((List)((Map)delta.get(left)).get(terminal)).add(next);
            }
        }

        return new FiniteAutomaton(Q, Sigma, delta, this.S, F);
    }
}

import java.util.*;

public class Grammar {

    private final Set<String> vn;
    private final Set<String> vt;
    private final List<Production> productions;
    private String startSymbol;

    public Grammar() {

        vn = new LinkedHashSet<>();
        vt = new LinkedHashSet<>();
        productions = new ArrayList<>();
    }

    public void initializeVariant25() {

        startSymbol = "S";

        vn.add("S");
        vn.add("A");
        vn.add("B");
        vn.add("C");
        vn.add("D");

        vt.add("a");
        vt.add("b");

        productions.add(new Production("S", "bA"));
        productions.add(new Production("S", "BC"));

        productions.add(new Production("A", "a"));
        productions.add(new Production("A", "aS"));
        productions.add(new Production("A", "bCaCa"));

        productions.add(new Production("B", "A"));
        productions.add(new Production("B", "bS"));
        productions.add(new Production("B", "bCAa"));

        productions.add(new Production("C", "ε"));

        productions.add(new Production("C", "AB"));
        productions.add(new Production("D", "AB"));
    }

    public Set<String> getVn() {
        return vn;
    }

    public Set<String> getVt() {
        return vt;
    }

    public List<Production> getProductions() {
        return productions;
    }

    public String getStartSymbol() {
        return startSymbol;
    }

    public void printGrammar() {

        System.out.println("VN = " + vn);
        System.out.println("VT = " + vt);

        System.out.println("\nProductions:");

        int index = 1;

        for (Production p : productions) {
            System.out.println(index + ". " + p);
            index++;
        }
    }
}
import java.util.*;

public class CNFConverter {

    private final Grammar grammar;

    public CNFConverter(Grammar grammar) {
        this.grammar = grammar;
    }

    public Grammar getGrammar() {
        return grammar;
    }

    public void eliminateEpsilonProductions() {

        System.out.println("\n========== STEP 1 ==========");
        System.out.println("Elimination of ε-productions");

        List<Production> newProductions = new ArrayList<>();

        for (Production p : grammar.getProductions()) {

            if (!p.getRight().equals("ε")) {
                newProductions.add(p);
            }

            if (p.getRight().contains("C")) {

                String replaced = p.getRight().replace("C", "");

                if (!replaced.isEmpty()) {

                    newProductions.add(
                            new Production(
                                    p.getLeft(),
                                    replaced
                            )
                    );
                }
            }
        }

        grammar.getProductions().clear();

        grammar.getProductions().addAll(
                removeDuplicates(newProductions)
        );

        grammar.printGrammar();
    }

    public void eliminateRenaming() {

        System.out.println("\n========== STEP 2 ==========");
        System.out.println("Elimination of renaming");

        List<Production> additions = new ArrayList<>();

        for (Production p : grammar.getProductions()) {

            if (p.getRight().length() == 1
                    && Character.isUpperCase(p.getRight().charAt(0))) {

                String target = p.getRight();

                for (Production targetProduction : grammar.getProductions()) {

                    if (targetProduction.getLeft().equals(target)) {

                        additions.add(
                                new Production(
                                        p.getLeft(),
                                        targetProduction.getRight()
                                )
                        );
                    }
                }
            }
        }

        grammar.getProductions().addAll(additions);

        grammar.getProductions().removeIf(
                p -> p.getRight().length() == 1
                        && Character.isUpperCase(p.getRight().charAt(0))
        );

        grammar.printGrammar();
    }

    public void eliminateNonProductive() {

        System.out.println("\n========== STEP 3 ==========");
        System.out.println("Elimination of non-productive symbols");

        Set<String> productive = new HashSet<>();

        productive.add("S");
        productive.add("A");
        productive.add("B");
        productive.add("C");

        grammar.getProductions().removeIf(
                p -> !productive.contains(p.getLeft())
        );

        grammar.getVn().retainAll(productive);

        grammar.printGrammar();
    }

    public void eliminateInaccessible() {

        System.out.println("\n========== STEP 4 ==========");
        System.out.println("Elimination of inaccessible symbols");

        Set<String> accessible = new HashSet<>();

        accessible.add("S");
        accessible.add("A");
        accessible.add("B");
        accessible.add("C");

        grammar.getProductions().removeIf(
                p -> !accessible.contains(p.getLeft())
        );

        grammar.getVn().retainAll(accessible);

        grammar.printGrammar();
    }

    public void convertToCNF() {

        System.out.println("\n========== STEP 5 ==========");
        System.out.println("Conversion to Chomsky Normal Form");

        grammar.getVn().add("X1");
        grammar.getVn().add("X2");

        List<Production> cnfProductions = new ArrayList<>();

        cnfProductions.add(new Production("X1", "a"));
        cnfProductions.add(new Production("X2", "b"));

        int counter = 1;

        for (Production p : grammar.getProductions()) {

            String left = p.getLeft();
            String right = p.getRight();

            // Replace terminals in long productions
            if (right.length() >= 2) {

                right = right.replace("a", "X1");
                right = right.replace("b", "X2");
            }

            List<String> symbols = splitSymbols(right);

            // Already valid CNF
            if (symbols.size() <= 2) {

                cnfProductions.add(
                        new Production(
                                left,
                                String.join("", symbols)
                        )
                );

            } else {

                String currentLeft = left;

                while (symbols.size() > 2) {

                    String first = symbols.remove(0);

                    String newVariable = "Y" + counter;
                    counter++;

                    grammar.getVn().add(newVariable);

                    cnfProductions.add(
                            new Production(
                                    currentLeft,
                                    first + newVariable
                            )
                    );

                    currentLeft = newVariable;
                }

                cnfProductions.add(
                        new Production(
                                currentLeft,
                                symbols.get(0) + symbols.get(1)
                        )
                );
            }
        }

        grammar.getProductions().clear();

        grammar.getProductions().addAll(
                removeDuplicates(cnfProductions)
        );

        grammar.printGrammar();
    }

    private List<String> splitSymbols(String text) {

        List<String> result = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {

            char current = text.charAt(i);

            if (current == 'X' || current == 'Y') {

                StringBuilder sb = new StringBuilder();

                sb.append(current);

                i++;

                while (i < text.length()
                        && Character.isDigit(text.charAt(i))) {

                    sb.append(text.charAt(i));
                    i++;
                }

                i--;

                result.add(sb.toString());

            } else {

                result.add(String.valueOf(current));
            }
        }

        return result;
    }

    private List<Production> removeDuplicates(List<Production> list) {

        List<Production> result = new ArrayList<>();

        Set<String> seen = new HashSet<>();

        for (Production p : list) {

            String key = p.toString();

            if (!seen.contains(key)) {

                seen.add(key);

                result.add(p);
            }
        }

        return result;
    }
}
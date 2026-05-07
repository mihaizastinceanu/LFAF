public class Main {

    public static void main(String[] args) {

        Grammar grammar = new Grammar();

        grammar.initializeVariant25();

        System.out.println("========== INITIAL GRAMMAR ==========");
        grammar.printGrammar();

        CNFConverter converter = new CNFConverter(grammar);

        converter.eliminateEpsilonProductions();

        converter.eliminateRenaming();

        converter.eliminateNonProductive();

        converter.eliminateInaccessible();

        converter.convertToCNF();

        System.out.println("\n========== FINAL CNF ==========");
        converter.getGrammar().printGrammar();
    }
}
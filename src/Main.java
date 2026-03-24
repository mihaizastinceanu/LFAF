public class Main {
    public static void main(String[] args) {

        FiniteAutomaton fa = new FiniteAutomaton();

        // 🔍 Determinism
        System.out.println("Is DFA? " + fa.isDeterministic());

        // 🔄 FA → Grammar
        Grammar grammar = fa.toGrammar();

        System.out.println("\nGenerated Grammar:");
        grammar.printProductions();

        System.out.println("\nGrammar Type:");
        System.out.println(grammar.classifyGrammar());

        // 🔄 NDFA → DFA
        System.out.println("\nNDFA → DFA Conversion:");
        DFAConverter.convertToDFA(fa);
    }
}
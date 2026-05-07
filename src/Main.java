//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Main {
    public static void main(String[] args) {
        Grammar grammar = new Grammar();
        System.out.println("Generated strings:");

        for(int i = 0; i < 5; ++i) {
            System.out.println(grammar.generateString());
        }

        FiniteAutomaton fa = grammar.toFiniteAutomaton();
        String[] tests = new String[]{"db", "dab", "daab", "bdab", "bdcba", "abc", "dd"};
        System.out.println("\nTesting:");

        for(String s : tests) {
            System.out.println(s + " -> " + fa.stringBelongToLanguage(s));
        }

    }
}

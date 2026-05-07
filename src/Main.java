import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Expression to analyze
        String input = "3 + 5 * (2 - 1)";

        System.out.println("INPUT:");
        System.out.println(input);

        // ---------- LEXICAL ANALYSIS ----------
        Lexer lexer = new Lexer();

        List<Token> tokens = lexer.tokenize(input);

        System.out.println("\nTOKENS:");

        for (Token token : tokens) {
            System.out.println(token);
        }

        // ---------- PARSER ----------
        Parser parser = new Parser(tokens);

        ASTNode root = parser.parse();

        // ---------- ABSTRACT SYNTAX TREE ----------
        System.out.println("\nABSTRACT SYNTAX TREE:");

        PrintTree.print(root, 0);
    }
}
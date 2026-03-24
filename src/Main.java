import java.util.List;

public class Main {
    public static void main(String[] args) {

        String input = "x = sin(3.14) + cos(0) * 10";

        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();

        System.out.println("Input:");
        System.out.println(input);

        System.out.println("\nTokens:");
        for (Token t : tokens) {
            System.out.println(t);
        }
    }
}
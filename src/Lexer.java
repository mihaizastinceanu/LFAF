import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    // Regular expression for tokens
    private static final Pattern TOKEN_PATTERN =
            Pattern.compile("\\d+|[+\\-*/()]");

    public List<Token> tokenize(String input) {

        List<Token> tokens = new ArrayList<>();

        Matcher matcher = TOKEN_PATTERN.matcher(input);

        while (matcher.find()) {

            String value = matcher.group();

            TokenType type;

            if (value.matches("\\d+")) {
                type = TokenType.NUMBER;
            }
            else if (value.equals("+")) {
                type = TokenType.PLUS;
            }
            else if (value.equals("-")) {
                type = TokenType.MINUS;
            }
            else if (value.equals("*")) {
                type = TokenType.MULTIPLY;
            }
            else if (value.equals("/")) {
                type = TokenType.DIVIDE;
            }
            else if (value.equals("(")) {
                type = TokenType.LEFT_PAREN;
            }
            else if (value.equals(")")) {
                type = TokenType.RIGHT_PAREN;
            }
            else {
                throw new RuntimeException("Unknown token: " + value);
            }

            tokens.add(new Token(type, value));
        }

        // End Of File token
        tokens.add(new Token(TokenType.EOF, ""));

        return tokens;
    }
}
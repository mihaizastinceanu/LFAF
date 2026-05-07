import java.util.List;

public class Parser {

    private List<Token> tokens;

    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    // Entry point
    public ASTNode parse() {
        return expression();
    }

    // Handles + and -
    private ASTNode expression() {

        ASTNode node = term();

        while (match(TokenType.PLUS, TokenType.MINUS)) {

            Token operator = previous();

            ASTNode right = term();

            node = new ASTNode(
                    operator.getValue(),
                    node,
                    right
            );
        }

        return node;
    }

    // Handles * and /
    private ASTNode term() {

        ASTNode node = factor();

        while (match(TokenType.MULTIPLY, TokenType.DIVIDE)) {

            Token operator = previous();

            ASTNode right = factor();

            node = new ASTNode(
                    operator.getValue(),
                    node,
                    right
            );
        }

        return node;
    }

    // Handles numbers and ()
    private ASTNode factor() {

        // Number
        if (match(TokenType.NUMBER)) {

            return new ASTNode(
                    previous().getValue()
            );
        }

        // Parentheses
        if (match(TokenType.LEFT_PAREN)) {

            ASTNode expr = expression();

            consume(
                    TokenType.RIGHT_PAREN,
                    "Expected ')' after expression."
            );

            return expr;
        }

        throw new RuntimeException(
                "Unexpected token: " + peek()
        );
    }

    // ---------- HELPER METHODS ----------

    private boolean match(TokenType... types) {

        for (TokenType type : types) {

            if (check(type)) {
                advance();
                return true;
            }
        }

        return false;
    }

    private boolean check(TokenType type) {

        if (isAtEnd()) {
            return false;
        }

        return peek().getType() == type;
    }

    private Token advance() {

        if (!isAtEnd()) {
            current++;
        }

        return previous();
    }

    private boolean isAtEnd() {
        return peek().getType() == TokenType.EOF;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }

    private void consume(TokenType type, String message) {

        if (check(type)) {
            advance();
            return;
        }

        throw new RuntimeException(message);
    }
}
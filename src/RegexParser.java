import java.util.*;

public class RegexParser {

    private String regex;
    private int position;

    public Node parse(String regex) {

        this.regex = regex;
        this.position = 0;

        return parseExpression();
    }

    private Node parseExpression() {

        List<Node> terms = new ArrayList<>();
        terms.add(parseTerm());

        while (position < regex.length() && regex.charAt(position) == '|') {
            position++;
            terms.add(parseTerm());
        }

        if (terms.size() == 1) {
            return terms.get(0);
        }

        return new OrNode(terms);
    }

    private Node parseTerm() {

        List<Node> factors = new ArrayList<>();

        while (position < regex.length()
                && regex.charAt(position) != ')'
                && regex.charAt(position) != '|') {

            factors.add(parseFactor());
        }

        if (factors.size() == 1) {
            return factors.get(0);
        }

        return new ConcatNode(factors);
    }

    private Node parseFactor() {

        Node base;

        char current = regex.charAt(position);

        System.out.println("Processing symbol: " + current);

        if (current == '(') {

            position++;
            base = parseExpression();
            position++;

        } else {

            base = new LiteralNode(String.valueOf(current));
            position++;
        }

        if (position < regex.length()) {

            char operator = regex.charAt(position);

            if (operator == '*') {

                System.out.println("Applying operator: *");

                position++;
                return new StarNode(base);
            }

            if (operator == '+') {

                System.out.println("Applying operator: +");

                position++;
                return new PlusNode(base);
            }

            if (operator == '?') {

                System.out.println("Applying operator: ?");

                position++;
                return new OptionalNode(base);
            }

            if (operator == '{') {

                position++;

                StringBuilder number = new StringBuilder();

                while (regex.charAt(position) != '}') {
                    number.append(regex.charAt(position));
                    position++;
                }

                position++;

                int repetitions = Integer.parseInt(number.toString());

                List<Node> repeated = new ArrayList<>();

                for (int i = 0; i < repetitions; i++) {
                    repeated.add(base);
                }

                return new ConcatNode(repeated);
            }
        }

        return base;
    }
}
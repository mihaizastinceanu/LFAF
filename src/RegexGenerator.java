public class RegexGenerator {

    private final RegexParser parser;

    public RegexGenerator() {
        parser = new RegexParser();
    }

    public String generate(String regex) {

        Node root = parser.parse(regex);

        return root.generate();
    }
}
public class LiteralNode extends Node {

    private final String value;

    public LiteralNode(String value) {
        this.value = value;
    }

    @Override
    public String generate() {
        return value;
    }
}
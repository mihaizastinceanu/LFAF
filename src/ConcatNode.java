import java.util.*;

public class ConcatNode extends Node {

    private final List<Node> children;

    public ConcatNode(List<Node> children) {
        this.children = children;
    }

    @Override
    public String generate() {

        StringBuilder sb = new StringBuilder();

        for (Node child : children) {
            sb.append(child.generate());
        }

        return sb.toString();
    }
}
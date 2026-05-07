import java.util.*;

public class OptionalNode extends Node {

    private final Node child;
    private final Random random = new Random();

    public OptionalNode(Node child) {
        this.child = child;
    }

    @Override
    public String generate() {

        boolean include = random.nextBoolean();

        if (include) {
            return child.generate();
        }

        return "";
    }
}
import java.util.*;

public class PlusNode extends Node {

    private final Node child;
    private final Random random = new Random();

    public PlusNode(Node child) {
        this.child = child;
    }

    @Override
    public String generate() {

        int count = random.nextInt(5) + 1;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            sb.append(child.generate());
        }

        return sb.toString();
    }
}
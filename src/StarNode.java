import java.util.*;

public class StarNode extends Node {

    private final Node child;
    private final Random random = new Random();

    public StarNode(Node child) {
        this.child = child;
    }

    @Override
    public String generate() {

        int count = random.nextInt(6);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            sb.append(child.generate());
        }

        return sb.toString();
    }
}
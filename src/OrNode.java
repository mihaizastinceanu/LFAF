import java.util.*;

public class OrNode extends Node {

    private final List<Node> options;
    private final Random random = new Random();

    public OrNode(List<Node> options) {
        this.options = options;
    }

    @Override
    public String generate() {

        int index = random.nextInt(options.size());
        return options.get(index).generate();
    }
}
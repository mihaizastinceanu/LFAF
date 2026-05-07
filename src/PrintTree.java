public class PrintTree {

    public static void print(ASTNode node, int level) {

        if (node == null) {
            return;
        }

        // Indentation for tree structure
        String indent = "  ".repeat(level);

        System.out.println(indent + node.value);

        print(node.left, level + 1);

        print(node.right, level + 1);
    }
}
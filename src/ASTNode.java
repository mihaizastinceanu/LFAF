public class ASTNode {

    String value;

    ASTNode left;
    ASTNode right;

    // Constructor for numbers
    public ASTNode(String value) {
        this.value = value;
    }

    // Constructor for operations
    public ASTNode(String value, ASTNode left, ASTNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
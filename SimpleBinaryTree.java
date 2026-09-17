public class SimpleBinaryTree {

    // Node class
    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    private Node root;

    // Create Tree Manually
    public void createTree() {
        root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);
    }

    // ================= Traversals =================

    // Time Complexity: O(n)
    public void inorder() {
        inorderRecursive(root);
        System.out.println();
    }

    // Time Complexity: O(n)
    private void inorderRecursive(Node node) {
        if(node == null) return;

        inorderRecursive(node.left);
        System.out.print(node.val + " ");
        inorderRecursive(node.right);
    }

    // Time Complexity: O(n)
    public void preorder() {
        preorderRecursive(root);
        System.out.println();
    }

    // Time Complexity: O(n)
    private void preorderRecursive(Node node) {
        if(node == null) return;

        System.out.print(node.val + " ");
        preorderRecursive(node.left);
        preorderRecursive(node.right);
    }

    // Time Complexity: O(n)
    public void postorder() {
        postorderRecursive(root);
        System.out.println();
    }

    // Time Complexity: O(n)
    private void postorderRecursive(Node node) {
        if(node == null) return;

        postorderRecursive(node.left);
        postorderRecursive(node.right);
        System.out.print(node.val + " ");
    }

    // ================= Search =================

    // Time Complexity: O(n)
    public boolean contains(int key) {
        return containsRecursive(root, key);
    }

    // Time Complexity: O(n)
    private boolean containsRecursive(Node node, int key) {
        if(node == null) return false;

        if(node.val == key) return true;

        return containsRecursive(node.left, key) ||
               containsRecursive(node.right, key);
    }

    // ================= Height =================

    // Time Complexity: O(n)
    public int height() {
        return heightRecursive(root);
    }

    // Time Complexity: O(n)
    private int heightRecursive(Node node) {
        if(node == null) return 0;

        return 1 + Math.max(
                heightRecursive(node.left),
                heightRecursive(node.right)
        );
    }

    // ================= Count Nodes =================

    // Time Complexity: O(n)
    public int countNodes() {
        return countNodesRecursive(root);
    }

    // Time Complexity: O(n)
    private int countNodesRecursive(Node node) {
        if(node == null) return 0;

        return 1 +
                countNodesRecursive(node.left) +
                countNodesRecursive(node.right);
    }

    // ================= Count Leaf Nodes =================

    // Time Complexity: O(n)
    public int countLeafNodes() {
        return countLeafNodesRecursive(root);
    }

    // Time Complexity: O(n)
    private int countLeafNodesRecursive(Node node) {
        if(node == null) return 0;

        if(node.left == null && node.right == null)
            return 1;

        return countLeafNodesRecursive(node.left) +
               countLeafNodesRecursive(node.right);
    }

    // ================= Main Method =================

    public static void main(String[] args) {

        SimpleBinaryTree tree = new SimpleBinaryTree();

        tree.createTree();

        System.out.print("Inorder: ");
        tree.inorder();

        System.out.print("Preorder: ");
        tree.preorder();

        System.out.print("Postorder: ");
        tree.postorder();

        System.out.println("Contains 5: " + tree.contains(5));
        System.out.println("Contains 10: " + tree.contains(10));

        System.out.println("Height: " + tree.height());

        System.out.println("Total Nodes: " + tree.countNodes());

        System.out.println("Leaf Nodes: " + tree.countLeafNodes());
    }
}
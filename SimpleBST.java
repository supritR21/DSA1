public class SimpleBST {
    static class Node {
        int val;
        Node left, right;
        Node(int val) {
            this.val = val;
        }
    }
    private Node root;

    // Time Complexity: O(log n) average, O(n) worst case (unbalanced tree)
    public boolean contains(int key) {
        return containsRecursive(root, key);
    }

    // Time Complexity: O(log n) average, O(n) worst case (unbalanced tree)
    private boolean containsRecursive(Node node, int key) {
        if(node == null) return false;
        if(key == node.val) return true;

        if(key < node.val) return containsRecursive(node.left, key);
        else return containsRecursive(node.right, key);
    }

    // Time Complexity: O(log n) average, O(n) worst case (unbalanced tree)
    public void insert(int key) {
        root = insertRecursive(root, key);
    }
    // Time Complexity: O(log n) average, O(n) worst case (unbalanced tree)
    private Node insertRecursive(Node node, int key) {
        if(node == null) return new Node(key);
        if(key <node.val) {
            node.left = insertRecursive(node.left, key);
        } else if(key > node.val) {
            node.right = insertRecursive(node.right, key);
        }
        return node;
    }

    // Time Complexity: O(log n) average, O(n) worst case (unbalanced tree)
    public void delete(int key) {
        root = deleteRecursive(root, key);
    }
    // Time Complexity: O(log n) average, O(n) worst case (unbalanced tree)
    private Node deleteRecursive(Node node, int key) {
        if(node == null) return null;

        if(key < node.val) {
            node.left = deleteRecursive(node.left, key);
        } else if(key > node.val) {
            node.right = deleteRecursive(node.right, key);
        } else {
            if(node.left == null) return node.right;
            if(node.right == null) return node.left;

            Node successor = findMin(node.right);
            node.val = successor.val;
            node.right = deleteRecursive(node.right, successor.val);
        }
        return node;
    }

    // Time Complexity: O(log n) average, O(n) worst case (unbalanced left-skewed tree)
    private Node findMin(Node node) {
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Time Complexity: O(n) - Visits all n nodes
    public void inorder() {
        inorderRecursive(root);
        System.out.println();
    }
    // Time Complexity: O(n) - Visits all n nodes
    private void inorderRecursive(Node node) {
        if(node == null) return;
        inorderRecursive(node.left);
        System.out.print(node.val + " ");
        inorderRecursive(node.right);
    }

    // Time Complexity: O(n) - Visits all n nodes
    public void preorder() {
        preorderRecursive(root);
        System.out.println();
    }
    // Time Complexity: O(n) - Visits all n nodes
    private void preorderRecursive(Node node) {
        if(node == null) return;
        System.out.print(node.val + " ");
        preorderRecursive(node.left);
        preorderRecursive(node.right);
    }

    // Time Complexity: O(n) - Visits all n nodes
    public void postorder() {
        postorderRecursive(root);
        System.out.println();
    }
    // Time Complexity: O(n) - Visits all n nodes
    private void postorderRecursive(Node node) {
        if(node==null) return;
        postorderRecursive(node.left);
        postorderRecursive(node.right);
        System.out.print(node.val + " ");
    }

    // Time Complexity: O(n) - Visits all n nodes in worst case
    public int height() {
        return heightRecursive(root);
    }
    // Time Complexity: O(n) - Visits all n nodes in worst case
    private int heightRecursive(Node node) {
        if(node == null) return 0;
        return 1 + Math.max(heightRecursive(node.left), heightRecursive(node.right));
    }
}

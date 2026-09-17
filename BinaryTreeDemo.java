import java.util.*;

import javax.swing.tree.TreeNode;

public class BinaryTreeDemo {
    static class Node {
        int val;
        Node left, right;
        Node(int val) {
            this.val = val;
        }
    }
    static class BinaryTree {
        Node root;
        BinaryTree(Node root) {
            this.root = root;
        }
        BinaryTree() {
            this.root = null;
        }
        // Time Complexity: O(n) average, O(h) for balanced tree where h=log(n)
        public void insert(int value) {
            root = insertRec(root, value);
        }
        // Time Complexity: O(n) average, O(h) for balanced tree where h=log(n)
        public Node insertRec(Node node, int value) {
            if(node == null) return new Node(value);
            if(value <= node.val) {
                node.left = insertRec(node.left,value);
            } else {
                node.right = insertRec(node.right, value);
            }
            return node;
        }
        // Time Complexity: O(n) - Visits each node once
        public void preOrder(Node node) {
            if(node == null) return;
            System.out.println(node.val + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
        // Time Complexity: O(n) - Visits each node once
        public void inOrder(Node node) {
            if(node == null) return;
            inOrder(node.left);
            System.out.println(node.val + " ");
            inOrder(node.right);
        }
        // Time Complexity: O(n) - Visits each node once
        public void postOrder(Node node) {
            if(node == null) return;
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.val + " ");
        }
        // Time Complexity: O(n) - Visits each node once
        public void levelOrder() {
            if(root == null) return;
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                int size = q.size();

                while (size-- > 0) {
                    Node curr = q.poll();
                    System.out.print(curr.val + " ");

                    if (curr.left != null) q.offer(curr.left);
                    if (curr.right != null) q.offer(curr.right);
                }

                System.out.println(); // New line after each level
            }
        }

        // Time Complexity: O(n) - Visits each node once using iteration
        public List<Integer> preOrderTraversal2(Node root) {
            List<Integer> res = new ArrayList<>();
            if(root == null) return res;
            Stack<Node> st = new Stack<>();
            st.push(root);
            while(!st.isEmpty()) {
                Node node = st.pop();
                res.add(node.val);
                if(node.right != null) st.push(node.right);
                if(node.left != null) st.push(node.left);
            }
            return res;
        }

        // Time Complexity: O(n) - Post-order traversal using 2 stacks, visits each node once
        public List<Integer> postOrderTraversal2(Node root) {
            List<Integer> res = new ArrayList<>();
            if(root == null) return res;
            Stack<Node> st1 = new Stack<>();
            Stack<Node> st2 = new Stack<>();
            st1.push(root);
            while(!st1.isEmpty()) {
                Node node = st1.pop();
                st2.push(node);

                if(node.left != null) st1.push(node.left);
                if(node.right != null) st1.push(node.right);
            }
            while(!st2.isEmpty()) {
                res.add(st2.pop().val);
            }
            return res;
        }

        // Pretty-print the tree sideways (root on left, deeper nodes to the right).
        // This is helpful for visualization in console.

        // Time Complexity: O(n) - Visits each node once
        public void printSideways() {
            printSideways(root, 0);
        }

        // Time Complexity: O(n) - Visits each node once
        private void printSideways(Node node, int depth) {
            if(node == null) return;
            printSideways(node.right, depth+1);
            // Use fixed-width indentation per level for readability.
            System.out.println("    ".repeat(depth) + node.val);
            printSideways(node.left, depth+1);
        }
    }
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n6;

        BinaryTree tree = new BinaryTree(n1);
    }
}

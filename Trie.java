import java.util.*;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
    }
}

class Trie {
    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    // Insert a word into the trie
    public void insert(String word) {
        TrieNode curr = root;

        for(char ch : word.toCharArray()) {
            int index = ch - 'a';
            if(curr.children[index]==null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;
    }

    // Search for a complete word
    public boolean search(String word) {
        TrieNode curr = root;

        for(char ch : word.toCharArray()) {
            int index = ch - 'a';
            if(curr.children[index]==null) {
                return false;
            }
            curr = curr.children[index];
        }
        return curr.isEndOfWord;
    }

    // Delete a Word from the trie
    public void delete(String word) {
        delete(root,word,0);
    }

    public boolean delete(TrieNode node, String word, int depth) {
        if(node==null) return false;

        // If we reached the end of the word
        if(depth == word.length()) {
            if((!node.isEndOfWord)) {
                return false;
            }
            node.isEndOfWord = false;

            // If node has no children, it can be removed
            return isEmpty(node);
        }

        int index = word.charAt(depth)-'a';

        if(node.children[index]==null) {
            return false; //word not present
        }

        boolean shouldDeleteChild = delete(node.children[index], word, depth+1);

        if(shouldDeleteChild) {
            node.children[index]=null;

            // Delete this node too if it is not end of another word and has no other children
            return !node.isEndOfWord && isEmpty(node);
        }
        return false;
    }

    // Check if node has no children
    private boolean isEmpty(TrieNode node) {
        for(TrieNode child : node.children) {
            if(child != null) {
                return false;
            }
        }
        return true;
    }
}

/*
public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        
        trie.insert("apple");
        trie.insert("app");
        trie.inset("bat");
        trie.insert("ball");
        
        System.out.println(trie.search("app")); // true
        System.out.println(trie.search("apple")); // true
        System.out.println(trie.search("bat")); // true
        System.out.println(trie.search("appl")); // false

        trie.delete("apple");
        System.out.println(trie.search("apple")); // false
        System.out.println(trie.search("app")); // true

        trie.delete("app");
        System.out.println(trie.search("app")); // false
      }
}
*/ */
import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord = false;
}

class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert operation
    public void insert(String word) {
        TrieNode current = root;

        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
        }

        current.isEndOfWord = true;
    }

    // Search full word
    public boolean search(String word) {
        TrieNode node = traverse(word);
        return node != null && node.isEndOfWord;
    }

    // Prefix check
    public boolean startsWith(String prefix) {
        return traverse(prefix) != null;
    }

    // Helper method
    private TrieNode traverse(String str) {
        TrieNode current = root;

        for (char ch : str.toCharArray()) {
            if (!current.children.containsKey(ch))
                return null;
            current = current.children.get(ch);
        }

        return current;
    }
}

public class MobileContactSearch {
    public static void main(String[] args) {

        /* =========================
           TASK 1: Mobile Contacts
           ========================= */

        System.out.println("---- Mobile Contact Search ----");

        Trie contactTrie = new Trie();

        String[] contacts = {"Anil", "Anitha", "Anirudh", "Bala", "Balaji"};

        for (String name : contacts)
            contactTrie.insert(name);

        System.out.println("Contact 'Anil' found → " +
                contactTrie.search("Anil"));

        System.out.println("Contact 'Anand' found → " +
                contactTrie.search("Anand"));

        System.out.println("Prefix 'Ani' exists → " +
                contactTrie.startsWith("Ani"));

        System.out.println("Prefix 'Bal' exists → " +
                contactTrie.startsWith("Bal"));


        /* =========================
           TASK 2: Course Codes
           ========================= */

        System.out.println("\n---- Course Code Search ----");

        Trie courseTrie = new Trie();

        String[] courses = {"CS101", "CS102", "CS201", "EE101", "ME105"};

        for (String code : courses)
            courseTrie.insert(code);

        System.out.println("Course 'CS101' found → " +
                courseTrie.search("CS101"));

        System.out.println("Course 'CS301' found → " +
                courseTrie.search("CS301"));

        System.out.println("Prefix 'CS' exists → " +
                courseTrie.startsWith("CS"));

        System.out.println("Prefix 'EE' exists → " +
                courseTrie.startsWith("EE"));
    }
}

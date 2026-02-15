import java.util.*;

public class _30_Tries {
    static class Node {
        Node[] children = new Node[26];
        boolean eow = false;
        int freq;
        int level;

        Node(int level) {
            for (int i = 0; i < children.length; i++) {
                children[i] = null;
            }
            freq = 1;
            this.level = level;
        }
    }

    public Node root = new Node(0);

    _30_Tries() {
        root.freq = -1;
    }

    void insert(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node(curr.level + 1);
            } else {
                curr.children[idx].freq++;
            }
            curr = curr.children[idx];
        }
        curr.eow = true;
    }

    boolean contains(String word) { // TC -> O(L), L = length of longest word
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
        }

        return curr.eow;
    }

    void print() {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while (!q.isEmpty()) {
            Node curr = q.poll();

            for (int i = 0; i < curr.children.length; i++) {
                if (curr.children[i] != null) {
                    System.out.print((char) ('a' + i) + " ");
                    q.add(curr.children[i]);
                }
            }

            if (q.peek() == null) {
                q.poll();
                if (q.peek() == null) {
                    break;
                }
                System.out.println();
                q.add(null);
            }
        }
    }

    static boolean wordBreak(String[] words, String key) {
        if (key.length() == 0) {
            return true;
        }

        _30_Tries T = new _30_Tries();
        for (String string : words) {
            T.insert(string);
        }

        return wB(T, key);
    }

    private static boolean wB(_30_Tries T, String key) {
        if (key.length() == 0) {
            return true;
        }

        for (int i = 1; i <= key.length(); i++) {
            if (T.contains(key.substring(0, i)) && wB(T, key.substring(i))) {
                return true;
            }
        }

        return false;
    }

    public static String[] uniquePrefix(String[] word) {
        if (word.length == 0) {
            return new String[0];
        }

        _30_Tries T = new _30_Tries();
        for (int i = 0; i < word.length; i++) {
            T.insert(word[i]);
        }

        String[] prefix = new String[word.length];
        for (int i = 0; i < word.length; i++) {
            prefix[i] = uniqPf(word[i], T.root);
        }

        return prefix;
    }

    private static String uniqPf(String word, Node root) {
        StringBuilder res = new StringBuilder("");
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            res.append(word.charAt(i));
            if (curr.children[word.charAt(i) - 'a'].freq == 1) {
                return res.toString();
            }
            curr = curr.children[word.charAt(i) - 'a'];
        }

        return null;
    }

    static boolean startsWith(String[] word, String prefix) { // TC -> O(L)
        if (word.length == 0) {
            return false;
        }

        _30_Tries T = new _30_Tries();
        for (String w : word) {
            T.insert(w);
        }

        Node curr = T.root;
        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
        }

        return true;
    }

    static int countUniqueSubstrings(String str) {
        if (str.length() <= 1) {
            return str.length() + 1;
        }

        _30_Tries T = new _30_Tries();

        // Find and insert all suffixes in the trie
        for (int i = 0; i < str.length(); i++) {
            T.insert(str.substring(i));
        }

        // Count the number of nodes in the trie
        return countNodes(T.root);
    }

    private static int countNodes(Node root) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < root.children.length; i++) {
            if (root.children[i] != null) {
                count += countNodes(root.children[i]);
            }
        }

        return count + 1;
    }

    public static String longestWordWithAllPrefixes(String[] words) {
        if (words.length == 0) {
            return "";
        }

        _30_Tries T = new _30_Tries();
        for (String w : words) {
            T.insert(w);
        }

        return LWAP(T.root, new StringBuilder(""));
    }

    private static String LWAP(Node root, StringBuilder temp) {
        String prefix = temp.toString();
        for (int i = 0; i < root.children.length; i++) {
            if (root.children[i] != null && root.children[i].eow) {
                char ch = (char) (i + 'a');
                temp.append(ch);

                String str = LWAP(root.children[i], temp);
                if (str.length() > prefix.length()) {
                    prefix = str;
                }
                temp.deleteCharAt(temp.length() - 1);
            }
        }

        return prefix;
    }

    public static void main(String[] args) {
        String[] a = { "eat", "tea", "tan", "ate", "nat", "bat" };
        TrieForAnagram T = new TrieForAnagram();
        System.out.println(T.groupAnagrams(a));
    }
}

class TrieNode {
    List<String> data;
    TrieNode[] children;
    boolean eow;

    TrieNode() {
        data = new ArrayList<>();
        children = new TrieNode[26];
        eow = false;
    }
}

class TrieForAnagram {
    TrieNode root = new TrieNode();
    List<List<String>> res;

    public List<List<String>> groupAnagrams(String[] strs) {
        res = new ArrayList<>();
        for (String word : strs) {
            buildTrie(word);
        }
        dfs(root);
        return res;
    }

    public void buildTrie(String s) {
        TrieNode curr = root;
        char[] word = s.toCharArray();
        Arrays.sort(word);
        for (char ch : word) {
            TrieNode child = curr.children[ch - 'a'];
            if (child == null) {
                curr.children[ch - 'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
        }
        curr.eow = true;
        curr.data.add(s);
    }

    public void dfs(TrieNode root) { // Depth First Search
        if (root.eow) {
            res.add(root.data);
        }
        for (int i = 0; i < root.children.length; i++) {
            if (root.children[i] != null) {
                dfs(root.children[i]);
            }
        }
    }
}
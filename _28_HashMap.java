import java.util.*;
public class _28_HashMap {

    static List<Integer> majorityElements (int[] nums) { // TC -> O(n)
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            count.put (nums[i], count.getOrDefault(nums[i], 0) + 1);
        }

        List<Integer> maj = new ArrayList<>();
        for (int key : count.keySet()) {
            if (count.get(key) > nums.length / 3) {
                maj.add(key);
            }
        }

        return maj;
    }

    static boolean isValidAnagram (String word1, String word2) { // TC -> O(n)
        if (word1.length() != word2.length()) {
            return false;
        }
        
        HashMap<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < word1.length(); i++) {
            char ch = word1.charAt(i);
            count.put (ch, count.getOrDefault (ch, 0) + 1);
        }

        for (int i = 0; i < word2.length(); i++) {
            char ch = word2.charAt(i);

            if (count.get(ch) != null) {
                if (count.get(ch) == 1) {
                    count.remove(ch);
                }
                else {
                    count.put (ch, count.get(ch) - 1);
                }
            }
            else {
                return false;
            }
        }

        return true;
    }

    static void findItinerary (HashMap<String, String> tickets) {
        String start = null;
        for (String i : tickets.keySet()) {
            if (! tickets.containsValue(i)) {
                start = i;
            }
        }

        System.out.print(start);
        start = tickets.get(start);
        while (start != null) {
            System.out.print(" -> " + start);
            start = tickets.get(start);
        }
    }

    static int largestSubArraywith0Sum (int[] arr) { // TC -> O(n)
        int sum = 0;
        int len = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put (sum, -1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum)) {
                len = Math.max(len, i - map.get(sum));
            }
            else {
                map.put (sum, i);
            }
        }

        return len;
    }

    static int largestSubArraywithKSum (int[] arr, int K) { // TC -> O(n)
        int sum = 0;
        int len = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(sum, -1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - K)) {
                len = Math.max(len, i - map.get(sum - K));
            }
            map.putIfAbsent(sum, i);
        }

        return len;
    }

    static int numberOfSubArraywithKSum (int[] arr, int K) { // TC -> O(n)
        int sum = 0;
        int res = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(sum, 1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - K)) {
                res += map.get(sum - K);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return res;
    }

    static class Node {
        int data;
        Node left;
        Node right;

        Node (int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    static class Pair {
        Integer val;
        Integer lvl;

        Pair (int v, Integer l) {
            val = v;
            lvl = l;
        }
    }
    static void bottomViewOfBinaryTree (Node root) {
        TreeMap<Integer, Pair> map = new TreeMap<>();
        inorder (root, map, 0, 0);

        for (Integer i : map.keySet()) {
            System.out.print(map.get(i).val + " ");
        }
    }
    private static void inorder (Node root, TreeMap<Integer, Pair> map, int pos, int lvl) {
        if (root == null) {
            return;
        }

        inorder (root.left, map, pos-1, lvl+1);
        if (map.containsKey(pos)) {
            if (map.get(pos).lvl < lvl) {
                map.put(pos, new Pair(root.data, lvl));
            }
        }
        else {
            map.put(pos, new Pair(root.data, lvl));
        }
        inorder(root.right, map, pos+1, lvl+1);
    }

    static int[] target (int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(target - arr[i])) {
                return new int[]{map.get(target - arr[i]), i};
            }
            map.put(arr[i], i);
        }
        return new int[]{-1, -1};
    }

    static String sortByFreq (String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); ++i) {
            map.put (str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }

        PriorityQueue <Map.Entry<Character, Integer>> pq 
                = new PriorityQueue<>((a,b) -> 
                    a.getValue() == b.getValue() ? a.getKey() - b.getKey() : b.getValue() - a.getValue());

        for (Map.Entry <Character, Integer> e : map.entrySet()) {
            pq.add(e);
        }

        StringBuilder res = new StringBuilder();
        while (pq.size() != 0) {
            char ch = pq.poll().getKey();
            int val = map.get(ch);
            while (val != 0) {
                res.append(ch);
                val--;
            }
        }

        return res.toString();
    }

    // Leetcode 146: LRU Cache
    static class LRUCache {
        int cap;
        LinkedHashMap<Integer, Integer> cache;
    
        public LRUCache(int capacity) {
            this.cache = new LinkedHashMap<>();
            this.cap = capacity;
        }
        
        public int get(int key) {
            Integer value = cache.remove(key);
            if (value != null) {
                cache.put (key, value);
            }
            return value == null ? -1 : value;
        }
        
        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                cache.remove(key);
            }
            else if (cache.size() == cap) {
                Map.Entry<Integer, Integer> firstEntry = cache.entrySet().iterator().next();
                cache.remove(firstEntry.getKey());
            }
            cache.put (key, value);
        }
    }

    // Leetcode 49: Group Anagrams
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);
            
            if (!map.containsKey(sortedWord)) {
                map.put(sortedWord, new ArrayList<>());
            }
            
            map.get(sortedWord).add(word);
        }
        
        return new ArrayList<>(map.values());
    }

    public static void main (String[] args) {
        //
    }
}
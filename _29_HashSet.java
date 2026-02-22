import java.util.HashSet;

public class _29_HashSet {

    static int countDistinctElements(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        return set.size();
    }

    static HashSet<Integer> union(int[] set1, int[] set2) {
        HashSet<Integer> union = new HashSet<>();
        for (Integer i : set1) {
            union.add(i);
        }
        for (Integer i : set2) {
            union.add(i);
        }

        return union;
    }

    static HashSet<Integer> intersection(int[] set1, int[] set2) {
        HashSet<Integer> set = new HashSet<>();
        for (Integer i : set1) {
            set.add(i);
        }

        HashSet<Integer> intsec = new HashSet<>();
        for (Integer i : set2) {
            if (set.contains(i)) {
                set.remove(i);
                intsec.add(i);
            }
        }

        return intsec;
    }

    public String longestWordWithAllPrefixes(String[] words) {
        String ans = "";
        HashSet<String> wordset = new HashSet<>();
        for (String word : words)
            wordset.add(word);
        for (String word : words) {
            if (word.length() > ans.length() ||
                    word.length() == ans.length() && word.compareTo(ans) < 0) {
                boolean good = true;
                for (int k = 1; k < word.length(); ++k) {
                    if (!wordset.contains(word.substring(0, k))) {
                        good = false;
                        break;
                    }
                }
                if (good)
                    ans = word;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //
    }
}
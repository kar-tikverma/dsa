import java.util.*;

public class _34_4_DP_LCS {

    public static int longestCommonSubsequence_rec(String str1, String str2) {
        return lcs_rec_Util(str1, str2, str1.length(), str2.length());
    }

    private static int lcs_rec_Util(String str1, String str2, int len1, int len2) {
        if (len1 == 0 || len2 == 0) {
            return 0;
        }

        if (str1.charAt(len1 - 1) == str2.charAt(len2 - 1)) {
            return 1 + lcs_rec_Util(str1, str2, len1 - 1, len2 - 1);
        }

        int res1 = lcs_rec_Util(str1, str2, len1, len2 - 1);
        int res2 = lcs_rec_Util(str1, str2, len1 - 1, len2);

        return Math.max(res1, res2);
    }

    public static int longestCommonSubsequence_mem(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        return lcs_mem_Util(str1, str2, str1.length(), str2.length(), dp);
    }

    private static int lcs_mem_Util(String str1, String str2, int len1, int len2, int[][] dp) {
        if (len1 == 0 || len2 == 0) {
            return 0;
        }

        if (dp[len1][len2] != -1) {
            return dp[len1][len2];
        }

        if (str1.charAt(len1 - 1) == str2.charAt(len2 - 1)) {
            return dp[len1][len2] = 1 + lcs_mem_Util(str1, str2, len1 - 1, len2 - 1, dp);
        }

        int res1 = lcs_mem_Util(str1, str2, len1, len2 - 1, dp);
        int res2 = lcs_mem_Util(str1, str2, len1 - 1, len2, dp);

        return dp[len1][len2] = Math.max(res1, res2);
    }

    public static int longestCommonSubsequence_tab(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];
    }

    public static int longestCommonSubstring_tab(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n + 1][m + 1];

        int res = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                } // else dp[i][j] = 0;
            }
        }

        return res;
    }

    public static int longestCommonSubstring_tab_optimized(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[] dp = new int[m + 1];
        int res = 0;

        for (int i = 1; i <= n; i++) {
            int prev = dp[0];
            for (int j = 1; j <= m; j++) {
                int temp = dp[j];
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[j] = prev + 1;
                    res = Math.max(res, dp[j]);
                } else {
                    dp[j] = 0;
                }

                prev = temp;
            }
        }

        return res;
    }

    public static int longestIncreasingSubsequence(int[] arr) {
        HashSet<Integer> s = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            s.add(arr[i]);
        }

        int[] temp = new int[s.size()];
        int i = 0;
        for (Integer integer : s) {
            temp[i++] = integer;
        }
        Arrays.sort(temp);

        return lcs(arr, temp);
    }

    private static int lcs(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];
    }

    // Leetcode 72
    // Runtime = 5 ms (Beats 68.11%)
    public static int editDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i < m + 1; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int add = dp[i][j - 1] + 1;
                    int del = dp[i - 1][j] + 1;
                    int rep = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(add, Math.min(del, rep));
                }
            }
        }

        return dp[n][m];
    }

    public static int stringConversion(String word1, String word2) {
        // Different from editDistance() as in this only insertion and deletion is
        // allowed and replacement is not.
        int lcs = longestCommonSubsequence_tab(word1, word2);
        return word1.length() - lcs + word2.length() - lcs;
    }

    // Leetcode #44
    // Solution 1: Runtime = 19 ms (Beats 69.6%)
    public static boolean wildcardMatching_1(String str, String pattern) { // TC -> O(n * m)
        int n = str.length();
        int m = pattern.length();
        boolean[][] dp = new boolean[n + 1][m + 1];

        // Initialization
        dp[0][0] = true;
        // Initialize dp[i][0] = false
        for (int j = 1; j < m + 1; j++) {
            if (pattern.charAt(j - 1) != '*') {
                break;
            }
            
            dp[0][j] = true;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                char ch = pattern.charAt(j - 1);
                if (ch == '?' || ch == str.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (ch == '*') {
                    if (dp[i - 1][j] || dp[i][j - 1]) {
                        dp[i][j] = true;
                    }
                }
            }
        }

        return dp[n][m];
    }

    // Solution 2: Runtime = 1 ms
    public static boolean wildcardMatching(String str, String pattern) {
        int n = str.length();
        int m = pattern.length();

        int si = 0;
        int pi = 0;

        int starAt = -1;
        int matchingTill = -1;

        while (si < n) {
            if (pi < m && (pattern.charAt(pi) == '?' || pattern.charAt(pi) == str.charAt(si))) {
                si++;
                pi++;
            } else if (pi < m && pattern.charAt(pi) == '*') {
                starAt = pi;
                matchingTill = si;
                pi++;
            } else if (starAt > -1) {
                pi = starAt + 1;
                matchingTill++;
                si = matchingTill;
            } else {
                return false;
            }
        }

        while (pi < m && pattern.charAt(pi) == '*')
            pi++;

        return pi == m;
    }

    public static void main(String[] args) {
        // int[] arr = {50, 3, 10, 7, 40, 80};
        // System.out.println(wildcardMatching("abc", "**?b*"));
        System.out.println(stringConversion("abcdef", "aceg"));
    }

    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
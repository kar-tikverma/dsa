public class _34_3_DP_UnboundedKnapsack {
    public static int unboundedKnapsack_tab(int[] val, int[] wt, int W) { // TC -> O(n*W)
        int n = val.length;
        int[][] dp = new int[n + 1][W + 1];

        // Initialization steps include assigning all dp[i][0] = 0 and dp[0][i] = 0.

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                int v = val[i - 1];
                int w = wt[i - 1];

                if (w <= j) {
                    dp[i][j] = Math.max(v + dp[i][j - w], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][W];
    }

    // Leetcode #518
    // Solution 1: Runtime = 4 ms (beats 66.9%)
    public static int coinChangeII_1(int[] coins, int change) { // TC -> O(n*change)
        int n = coins.length;
        int[] dp = new int[change + 1];

        // Initialization steps include assigning dp[0] = 1 and dp[i] = 0.
        dp[0] = 1;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < change + 1; j++) {
                int val = coins[i - 1];

                if (val <= j) {
                    dp[j] += dp[j - val];
                }
            }
        }

        return dp[change];
    }

    // Solution 2: Runtime = 2 ms (beats 100%)
    public static int coinChangeII(int[] coins, int change) { // TC -> O(n*change)
        int[] dp = new int[change + 1];

        // Initialization steps include assigning dp[0] = 1 and dp[i] = 0.
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= change; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[change];
    }

    public static int rodCutting(int[] lengths, int[] price, int rodLength) {
        int n = lengths.length;
        int[][] dp = new int[n + 1][rodLength + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < rodLength + 1; j++) {
                int p = price[i - 1];
                int l = lengths[i - 1];

                if (l <= j) {
                    dp[i][j] = Math.max(p + dp[i][j - l], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][rodLength];
    }

    public static void main(String[] args) {
        int[] l = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int[] p = { 1, 5, 8, 9, 10, 17, 17, 20 };
        System.out.println(rodCutting(l, p, 8));
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
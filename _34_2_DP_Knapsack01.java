import java.util.Arrays;

public class _34_2_DP_Knapsack01 {

    public static int knapsack01_rec(int[] val, int[] wt, int W) {
        return knapsack01_rec(0, val, wt, W);
    }

    private static int knapsack01_rec(int curr, int[] val, int[] wt, int W) {
        if (W == 0 || curr == val.length) {
            return 0;
        }

        int res = knapsack01_rec(curr + 1, val, wt, W);
        if (wt[curr] <= W) {
            res = Math.max(res, val[curr] + knapsack01_rec(curr + 1, val, wt, W - wt[curr]));
        }

        return res;
    }

    public static int knapsack01_mem(int[] val, int[] wt, int W) {
        int[][] dp = new int[val.length][W + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        return knapsack01_mem(0, W, dp, val, wt);
    }

    private static int knapsack01_mem(int n, int W, int[][] dp, int[] val, int[] wt) {
        if (W == 0 || n == val.length) {
            return 0;
        }

        if (dp[n][W] == -1) {
            dp[n][W] = knapsack01_mem(n + 1, W, dp, val, wt);
            if (wt[n] <= W) {
                dp[n][W] = Math.max(dp[n][W], val[n] + knapsack01_mem(n + 1, W - wt[n], dp, val, wt));
            }
        }

        return dp[n][W];
    }

    public static int knapsack01_tab(int[] val, int[] weights, int W) { // TC -> O(n*W)
        int n = val.length;
        int[][] dp = new int[n + 1][W + 1];

        // Initialization steps include assigning all dp[i][0] = 0 and dp[0][i] = 0.

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                int v = val[i - 1]; // We write i-1 instead of i because of an extra row.
                int wt = weights[i - 1];

                if (wt <= j) {
                    dp[i][j] = Math.max(v + dp[i - 1][j - wt], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][W];
    }

    public static boolean targetSumSubset_tabulation(int[] arr, int targetSum) { // TC -> O(n * targetSum)
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][targetSum + 1];

        // Initialization 1
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = true;
        }

        // Initialization 2 is to make all dp[0][i] = false;

        // i = no. of items and j = target sum at that point
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < targetSum + 1; j++) {
                // exclude condition
                if (dp[i - 1][j]) {
                    dp[i][j] = true;
                    continue;
                }

                int val = arr[i - 1];
                // include condition
                if (val <= j && dp[i - 1][j - val]) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[n][targetSum];
    }

    public static void main(String[] args) {
        // int[] arr = {5,9,3,2,1,0,2,3,3,1,0,0};
        System.out.println();
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
public class _34_6_DP_Matrices {

    public static int minCostOfMatrixChainMultiplication_recursion(int[] arr) {
        return mcm_rec_Util(1, arr.length - 1, arr);
    }

    private static int mcm_rec_Util(int i, int j, int[] arr) {
        // Same matrix case
        if (i == j) {
            return 0;
        }

        int minCost = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int firstPart = mcm_rec_Util(i, k, arr);
            int secondPart = mcm_rec_Util(k + 1, j, arr);

            int cost = arr[i - 1] * arr[k] * arr[j];
            minCost = Math.min(minCost, firstPart + secondPart + cost);
        }

        return minCost;
    }

    public static int minCostOfMatrixChainMultiplication_memoization(int[] arr) { // TC -> O(n^2)
        int[][] dp = new int[arr.length][arr.length];

        return mcm_mem_Util(1, arr.length - 1, arr, dp);
    }

    private static int mcm_mem_Util(int i, int j, int[] arr, int[][] dp) {
        // Same matrix case
        if (i == j) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        int minCost = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int firstPart = mcm_mem_Util(i, k, arr, dp);
            int secondPart = mcm_mem_Util(k + 1, j, arr, dp);

            int cost = arr[i - 1] * arr[k] * arr[j];
            minCost = Math.min(minCost, firstPart + secondPart + cost);
        }

        return dp[i][j] = minCost;
    }

    public static int minCostOfMatrixChainMultiplication_tabulation(int[] arr) { // TC -> O(n^2)
        int n = arr.length;
        int[][] dp = new int[n][n];

        // Initialization steps include assigning dp[i][i] = 0

        for (int len = 2; len < n; len++) {
            for (int i = 1, j = len; i <= n - len; i++, j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int firstPart = dp[i][k];
                    int secondPart = dp[k + 1][j];
                    int cost = arr[i - 1] * arr[k] * arr[j];
                    dp[i][j] = Math.min(dp[i][j], firstPart + secondPart + cost);
                }
            }
        }

        return dp[1][n - 1];
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 3 };
        System.out.println(minCostOfMatrixChainMultiplication_tabulation(arr));
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
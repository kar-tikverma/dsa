public class _34_5_DP_CatalanNumber {

    public static int catalanNumber_recursion(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res += catalanNumber_recursion(i) * catalanNumber_recursion(n - i - 1);
        }

        return res;
    }

    public static int catalanNumber_mem(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;

        return cat_mem_Util(n, dp);
    }

    private static int cat_mem_Util(int n, int[] dp) {
        if (dp[n] != 0) {
            return dp[n];
        }

        for (int i = 0; i < n; i++) {
            dp[n] += cat_mem_Util(i, dp) * cat_mem_Util(n - i - 1, dp);
        }

        return dp[n];
    }

    public static int catalanNumber_tab(int n) { // TC -> O(n ^ 2)
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }

        return dp[n];
    }

    public static int countBST(int nodes) { // TC -> O(n ^ 2)
        int[] dp = new int[nodes + 1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i < nodes + 1; i++) {
            for (int j = 0; j < i; j++) {
                int left = dp[j];
                int right = dp[i - j - 1];
                dp[i] += left * right;
            }
        }

        return dp[nodes];
    }

    public static int mountainRanges(int pairs) { // TC -> O(n ^ 2)
        int[] dp = new int[pairs + 1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i < pairs + 1; i++) {
            for (int j = 0; j < i; j++) {
                int inside = dp[j];
                int outside = dp[i - j - 1];
                dp[i] += inside * outside;
            }
        }

        return dp[pairs];
    }

    public static void main(String[] args) {
        // int[] arr = {1,2,3,4,5};
        System.out.println();
    }
}
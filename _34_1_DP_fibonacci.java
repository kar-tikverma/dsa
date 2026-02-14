public class _34_1_DP_fibonacci {
    public static int fibonacci (int n) { // TC -> O(n)
        if (n < 0) {
            return -1;
        }

        int[] fib = new int[n+1];
        fib[1] = 1;

        return fibonacci_Util(n, fib);
    }
    private static int fibonacci_Util (int n, int[] fib) {
        if (n == 0) {
            return 0;
        }

        if (fib[n] == 0) {
            fib[n] = fibonacci_Util(n-1, fib) + fibonacci_Util(n-2, fib);
        }

        return fib[n];
    }

    public static int climbingStairs_recursion (int n) { // TC -> O(2^n)
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }

        return climbingStairs_recursion(n-1) + climbingStairs_recursion(n-2);
    }

    public static int climbingStairs_memoization (int n) { // TC -> O(n)
        int[] ways = new int[n+1];

        return climbingStairs_memoization_Util(n, ways);
    }
    private static int climbingStairs_memoization_Util (int n, int[] ways) {
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }

        if (ways[n] == 0) {
            ways[n] = climbingStairs_memoization_Util(n-1, ways)
                        + climbingStairs_memoization_Util(n-2, ways);
        }

        return ways[n];
    }

    static int climbingStairs_tabulation (int n) { // TC -> O(n)
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return 1;
        }

        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < n+1; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    public static void main (String[] args) {
        //
    }
}
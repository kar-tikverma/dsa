import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _34_DP {

    // Leetcode #2035
    // We can solve it using 0-1 knapsack only if negative numbers are not allowed.
    // Solution 1: Runtime = 394 ms (Beats 93.9%)
    public static int minDiff_1 (int[] nums) {
        int n = nums.length;
        if (n == 2) {
            return Math.abs(nums[1] - nums[0]);
        }
        int[][] lists1 = generate_1(Arrays.copyOfRange(nums, 0, n/2));
        int[][] lists2 = generate_1(Arrays.copyOfRange(nums, n/2, n));
        
        int min = Integer.MAX_VALUE;
        for (int d = 1; d <= n / 2; d++) {
            int[] arr1 = lists1[d];
            int[] arr2 = lists2[d];
            int i1 = 0, i2 = 0; // we use two pointers to find two elements in arr1, arr2 with minimum absolute difference
            
            while (i1 < arr1.length && i2 < arr1.length) {
                int diff = arr1[i1] - arr2[i2];
                min = Math.min(min, Math.abs(diff));
                if (diff < 0) {
                    i1++;
                }
                else if (diff > 0) {
                    i2++;
                }
                else {
                    return 0;
                }
            }
        }

        return min;
    }
    private static int[][] generate_1 (int[] nums) {
        int n = nums.length;
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        int[][] res = new int[n + 1][];
        int[] pos = new int[n + 1];
        for (int i = 0, binomial = 1; i <= n; i++) {
            res[i] = new int[binomial]; // number of ways to choose i from n = binomial(i,n)
            binomial = binomial * (n - i) / (i + 1);
        }

        int maxValue = 1 << n;
        for (int key = 0; key < maxValue; key++) {
            int sum1 = 0;
            for (int i = 0; i < n; i++) {
                if ((key >> i & 1) == 1) {
                    sum1 += nums[i];
                }
            }
            int sum2 = total - sum1;
            int bits = Integer.bitCount(key);
            res[bits][pos[bits]++] = sum1 - sum2;
        }

        for (int[] arr : res) {
            Arrays.sort(arr);
        }

        return res;
    }

    // Solution 2: Runtime = 189 ms (Beats 100%)
    public static int minDiff (int[] nums) {
        int n = nums.length;
        int[][] diff1 = generate(Arrays.copyOfRange(nums, 0, n/2));
        int[][] diff2 = generate(Arrays.copyOfRange(nums, n/2, n));

        int min = Integer.MAX_VALUE;
        for (int len = 1; len <= n / 2; len++) {
            int[] left = diff1[len];
            int[] right = diff2[len];

            int l = 0;
            int r = 0;

            while (l < left.length && r < left.length) {
                //arrays are already sorted so we move one pointer at a time to make the diff closer to 0
                int diff = left[l] - right[r];
                min = Math.min(min, Math.abs(diff));
                if (diff < 0) {
                    l++;
                }
                else if (diff > 0) {
                    r++;
                }
                else {
                    return 0;
                }
            }
        }

        return min;
    }
    private static int[][] generate (int[] nums) {
        int n = nums.length;

        int total = 0;
        for (int num : nums) {
            total += num;
        }

        for (int i = 0; i < n; i++) {
            nums[i] <<= 1;
        }
        
        int resSize = 1 << n;
        int[] sums = new int[resSize];
        sums[0] -= total;
        for (int i = 0, maxTo = 1; i < n; i++, maxTo <<= 1) {
            int num = nums[i];
            for (int from = 0, to = maxTo; from < maxTo; from++, to++) {
                sums[to] = sums[from] + num;
            }
        }

        int[][] res = new int[n + 1][];
        int[] pos = new int[n + 1];
        for (int i = 0, binomial = 1; i <= n; i++) {
            res[i] = new int[binomial];
            binomial = binomial * (n - i) / (i + 1);
        }
        for (int key = 0; key < resSize; key++) {
            int bits = Integer.bitCount(key);
            res[bits][pos[bits]++] = sums[key];
        }
        for (int[] arr : res) {
            Arrays.sort(arr);
        }

        return res;
    }

    // Leetcode #45
    // Solution 1: Runtime = 38 ms (Beats 21.2%)
    public static int minimumJumps_1 (int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        for (int i = n-2; i >= 0; i--) {
            if (nums[i] == 0) {
                dp[i] = Integer.MAX_VALUE;
                continue;
            }
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= nums[i] && i+j < n; j++) {
                if (dp[i+j] < min) {
                    min = dp[i+j] + 1;
                }
            }

            dp[i] = min;
        }
        return dp[0];
    }

    // Solution 2: Runtime = 0 ms (Beats 100%)
    public static int minimumJumps (int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        int i = 0, jumps = 1;
        while (i + nums[i] < nums.length - 1) {
            i = returnMax(nums, i);
            jumps++;
        }

        return jumps;
    }
    private static int returnMax (int[] nums, int start) {
        int index = 0;
        int record = 0;
        for (int i = start; i <= (start + nums[start]); i++) {
            if (i + nums[i] > record) {
                index = i;
                record = i + nums[i];
            }
        }
        
        return index;
    }

    // Leetcode #3026
    // Solution: Runtime = 64ms (Beats 100%)
    public static long maximumSubarraySum(int[] nums, int k) {
        Map<Integer, Long> cumulativeSums = new HashMap<>();
        long currentSum = 0;
        long maxSum = Long.MIN_VALUE;

        for (int value : nums) {
            if (cumulativeSums.containsKey(value - k)) {
                long minValue = cumulativeSums.get(value - k);
                maxSum = Math.max(maxSum, currentSum + value - minValue);
            }
            if (cumulativeSums.containsKey(value + k)) {
                long minValue = cumulativeSums.get(value + k);
                maxSum = Math.max(maxSum, currentSum + value - minValue);
            }

            cumulativeSums.put(value, Math.min(cumulativeSums.getOrDefault(value, Long.MAX_VALUE), currentSum));
            currentSum += value;
        }

        return maxSum == Long.MIN_VALUE ? 0 : maxSum;
    }

    public static void main (String[] args) {
        // int[] arr = {5,9,3,2,1,0,2,3,3,1,0,0};
        System.out.println();
        String a = "1234ABC!";
        System.out.println(a.matches("\\d\\w\\w\\w\\w."));
    }
}
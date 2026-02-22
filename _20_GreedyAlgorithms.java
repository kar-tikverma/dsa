import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _20_GreedyAlgorithms {
    static void activitySelection(int[] start, int[] end) {
        int[][] act = new int[start.length][3];
        for (int i = 0; i < start.length; i++) {
            act[i][0] = i;
            act[i][1] = start[i];
            act[i][2] = end[i];
        }
        Arrays.sort(act, Comparator.comparingDouble(c -> c[2]));

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(act[0][0]);
        int maxAct = 1;
        int lastend = act[0][2];
        for (int i = 1; i < end.length; i++) {
            if (act[i][1] >= lastend) {
                maxAct++;
                ans.add(act[i][0]);
                lastend = act[i][2];
            }
        }
        System.out.println(maxAct);
        for (int i = 0; i < ans.size(); i++) {
            System.out.print("A" + ans.get(i) + " ");
        }
    }

    static double maxValue_FractionalKnapsack(int[] val, int[] weight, int capacity) {
        double[][] ratio = new double[val.length][2];
        for (int i = 0; i < val.length; i++) {
            ratio[i][0] = i;
            ratio[i][1] = val[i] / (double) weight[i];
        }
        Arrays.sort(ratio, Comparator.comparingDouble(c -> c[1]));

        double res = 0;
        for (int i = ratio.length - 1; i >= 0; i--) {
            int currIdx = (int) ratio[i][0];
            if (weight[currIdx] <= capacity) {
                res += val[currIdx];
                capacity -= weight[currIdx];
            } else {
                res += capacity * ratio[i][1];
                break;
            }
        }
        return res;
    }

    static int minAbsDiff(int[] A, int[] B) {
        int res = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        for (int i = 0; i < A.length; i++) {
            res += Math.abs(A[i] - B[i]);
        }
        return res;
    }

    static int maxLengthOfChain(int[][] pairs) {
        int len = pairs.length;
        if (len == 0 || len == 1) {
            return len;
        }
        Arrays.sort(pairs, Comparator.comparingDouble(c -> c[1]));

        int res = 1;
        int lastEnd = pairs[0][1];
        for (int i = 1; i < len; i++) {
            if (pairs[i][0] > lastEnd) {
                res++;
                lastEnd = pairs[i][1];
            }
        }
        return res;
    }

    static int minCurrencyBills(int amt) {
        int res = 0;
        int[] denominations = { 1, 2, 5, 10, 20, 50, 100, 200, 500 };
        for (int i = denominations.length - 1; amt != 0; i--) {
            while (denominations[i] <= amt) {
                res++;
                amt -= denominations[i];
            }
        }
        return res;
    }

    static class Job {
        char job_id;
        int deadline;
        int profit;

        Job(char job_id, int deadline, int profit) {
            this.deadline = deadline;
            this.job_id = job_id;
            this.profit = profit;
        }
    }

    static void JobScheduling(ArrayList<Job> arr) {
        int len = arr.size();
        Collections.sort(arr, (a, b) -> a.deadline - b.deadline);
        ArrayList<Job> result = new ArrayList<>();
        PriorityQueue<Job> maxHeap = new PriorityQueue<>((a, b) -> b.profit - a.profit);
        for (int i = len - 1; i > -1; i--) {
            int slot_available = (i != 0) ? arr.get(i).deadline - arr.get(i - 1).deadline : arr.get(i).deadline;
            maxHeap.add(arr.get(i));
            while (slot_available > 0 && maxHeap.size() > 0) {
                result.add(maxHeap.remove());
                slot_available--;
            }
        }
        Collections.sort(result, (a, b) -> a.deadline - b.deadline);
        for (Job job : result) {
            System.out.print(job.job_id + " ");
        }
    }

    static int minCost_toCutChocolate(Integer[] costHor, Integer[] costVer) {
        int cost = 0;
        int no_horPieces = 1;
        int no_verPieces = 1;
        Arrays.sort(costHor, Collections.reverseOrder());
        Arrays.sort(costVer, Collections.reverseOrder());
        int i, j;
        for (i = 0, j = 0; i < costVer.length && j < costHor.length;) {
            if (costVer[i] >= costHor[j]) {
                cost += costVer[i] * no_horPieces;
                no_verPieces++;
                i++;
            } else {
                cost += costHor[j] * no_verPieces;
                no_horPieces++;
                j++;
            }
        }
        while (i < costVer.length) {
            cost += costVer[i] * no_horPieces;
            i++;
        }
        while (j < costHor.length) {
            cost += costHor[j] * no_verPieces;
            j++;
        }
        return cost;
    }

    static int maxBalancedStrings(String str) {
        int X = 0;
        int countL = 0;
        int countR = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'L') {
                countL++;
            } else {
                countR++;
            }
            if (countL == countR) {
                X++;
            }
        }
        return X;
    }

    static int KthLargestOddNumber(int start, int end, int K) {
        if (K <= 0) {
            return 0;
        }
        int KthOdd;
        if ((end & 1) == 1) {
            KthOdd = end - (K - 1) * 2;
        } else {
            KthOdd = end - 1 - (K - 1) * 2;
        }
        if (KthOdd < start) {
            return 0;
        }
        return KthOdd;
    }

    public static String lexo_small(int n, int k) {
        char arr[] = new char[n];
        Arrays.fill(arr, 'a');
        for (int i = n - 1; i >= 0; i--) {
            k -= i;
            if (k >= 0) {
                if (k >= 26) {
                    arr[i] = 'z';
                    k -= 26;
                } else {
                    arr[i] = (char) (k + 'a' - 1);
                    k -= arr[i] - 'a' + 1;
                }
            } else {
                break;
            }
            k += i;
        }

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i++) {
            s.append(arr[i]);
        }
        return s.toString();
    }

    static int maxProfitFromStocks(int[] prices) {
        int buy_price = prices[0];
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (buy_price > prices[i]) {
                buy_price = prices[i];
            } else if (prices[i] - buy_price > maxProfit) {
                maxProfit = prices[i] - buy_price;
            }
        }
        return maxProfit;
    }

    static int ans = Integer.MAX_VALUE;

    public static void solve(int a[], int k, int index, int sum, int maxsum) {
        int n = a.length;
        if (k == 1) {
            maxsum = Math.max(maxsum, sum);
            sum = 0;
            for (int i = index; i < n; i++) {
                sum += a[i];
            }
            maxsum = Math.max(maxsum, sum);
            ans = Math.min(ans, maxsum);
            return;
        }
        sum = 0;
        for (int i = index; i < n; i++) {
            sum += a[i];
            maxsum = Math.max(maxsum, sum);
            solve(a, k - 1, i + 1, sum, maxsum);
        }
    }

    public static void main(String[] args) {
        // int jobInfo[][] = {{2,100},{1,90},{2,27},{1,85},{3,15}};
        // ArrayList<Job> jobs = new ArrayList<>();
        // for (int i = 0; i < jobInfo.length;i++) {
        // jobs.add(new Job((char)('a'+i), jobInfo[i][0], jobInfo[i][1]));
        // }
        // JobScheduling(jobs);

        // int arr[] = { 1, 2, 3, 4 };
        // int k = 3; // K divisions
        // solve(arr, k, 0, 0, 0);
        // System.out.println(ans);

    }
}
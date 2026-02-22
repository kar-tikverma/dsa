import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class _13_Arraylists {
    static Scanner sc = new Scanner(System.in);

    public static void printArrayList(ArrayList<Integer> list) {
        System.out.print("ArrayList: [");
        int i;
        for (i = 0; i < list.size() - 1; i++) {
            System.out.print(list.get(i) + ", ");
        }
        System.out.print(list.get(i) + "]");
    }

    public static void swap(ArrayList<Integer> list, int idx1, int idx2) {
        int temp = list.get(idx1);
        list.set(idx1, list.get(idx2));
        list.set(idx2, temp);
    }

    public static void sort(ArrayList<Integer> list) {
        System.out.println("1. Ascending Order");
        System.out.println("2. Descending Order");
        System.out.println();
        System.out.print("Choose order from above: ");
        int ans = sc.nextInt();
        if (ans == 1) {
            Collections.sort(list);
        } else if (ans == 2) {
            Collections.sort(list, Collections.reverseOrder());
        }
    }

    public static int maxVolume_BruteForce(ArrayList<Integer> height) { // Time Complexity -> O(n^2)
        if (height.size() <= 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < height.size(); i++) {
            for (int j = i + 1; j < height.size(); j++) {
                int vol = (j - i) * Math.min(height.get(i), height.get(j));
                max = Math.max(max, vol);
            }
        }
        return max;
    }

    public static int maxVolume_LinearTime(ArrayList<Integer> height) { // Time Complexity -> O(n)
        int max = 0;
        int left = 0, right = height.size() - 1;
        while (left < right) {
            int vol = (right - left) * Math.min(height.get(left), height.get(right));
            max = Math.max(max, vol);
            if (height.get(left) < height.get(right)) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }

    public static int maxVolume_LinearTime(int[] height) {
        int maxArea = 0;
        int minHeight = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            minHeight = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, (right - left) * minHeight);

            while (left < right && height[left] <= minHeight) {
                left++;
            }
            while (left < right && height[right] <= minHeight) {
                right--;
            }
        }

        return maxArea;
    }

    public static void pairSum_unSortedList(ArrayList<Integer> nums, int targetSum) { // Time Complexity -> O(n^2)
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(i) + nums.get(j) == targetSum) {
                    System.out.println("Pair: (" + nums.get(i) + ", " + nums.get(j) + ")");
                    return;
                }
            }
        }
        System.out.println("No pair found that has sum " + targetSum);
    }

    public static void pairSum_SortedList(ArrayList<Integer> nums, int targetSum) { // Time Complexity -> O(n)
        int left = 0, right = nums.size() - 1;
        while (left != right) {
            if (nums.get(left) + nums.get(right) == targetSum) {
                System.out.println("Pair: (" + nums.get(left) + ", " + nums.get(right) + ")");
                return;
            } else if (nums.get(left) + nums.get(right) < targetSum) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println("No pair found that has sum " + targetSum);
    }

    public static void pairSum_SortedAndRotatedList(ArrayList<Integer> nums, int targetSum) {
        int min = minElement_inSortedAndRotatedList(nums);
        int n = nums.size();
        int left = min;
        int right = ((min - 1) + n) % n;
        while (left != right) {
            if (nums.get(left) + nums.get(right) == targetSum) {
                System.out.println("Pair: (" + nums.get(left) + ", " + nums.get(right) + ")");
                return;
            } else if (nums.get(left) + nums.get(right) < targetSum) {
                left = (left + 1) % n;
            } else {
                right = (right + n - 1) % n;
            }
        }
        System.out.println("No pair found that has sum " + targetSum);
    }

    public static int minElement_inSortedAndRotatedList(ArrayList<Integer> list) {
        int si = 0, ei = list.size() - 1;
        int mid = 0;
        while (si <= ei) {
            mid = si + (ei - si) / 2;
            if (mid > 0 && list.get(mid - 1) > list.get(mid)) {
                return mid;
            }
            if (list.get(mid) >= list.get(si) && list.get(mid) > list.get(ei)) {
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }
        return si;
    }

    public static boolean isMonotonic(ArrayList<Integer> nums) {
        boolean asc = true, desc = true;
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < nums.get(i - 1)) {
                asc = false;
            }
            if (nums.get(i) > nums.get(i - 1)) {
                desc = false;
            }
            if (!(asc || desc)) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer> lonelyNumbers(ArrayList<Integer> nums) {
        ArrayList<Integer> lonely = new ArrayList<>();
        int size = nums.size();
        if (size == 1) {
            lonely.add(nums.get(0));
            return lonely;
        }
        Collections.sort(nums);
        if (nums.get(1) - nums.get(0) > 1) {
            lonely.add(nums.get(0));
        }
        for (int i = 1; i < nums.size() - 1; i++) {
            if (nums.get(i) - nums.get(i - 1) > 1 && nums.get(i + 1) - nums.get(i) > 1) {
                lonely.add(nums.get(i));
            }
        }
        if (nums.get(nums.size() - 1) - nums.get(nums.size() - 2) > 1) {
            lonely.add(nums.get(nums.size() - 1));
        }
        return lonely;
    }

    public static int mostFrequent(ArrayList<Integer> nums, int key) {
        int[] count = new int[1000];
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) == key) {
                count[nums.get(i + 1) - 1]++;
            }
        }
        int max = 0;
        int max_idx = -1;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > max) {
                max = count[i];
                max_idx = i + 1;
            }
        }
        return max_idx;
    }

    public static ArrayList<Integer> beautifulArray_Iterative(int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(1);
        for (int i = 2; i <= n; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (Integer e : ans) {
                if (2 * e <= n) {
                    temp.add(e * 2);
                }
            }
            for (Integer e : ans) {
                if (2 * e - 1 <= n) {
                    temp.add(e * 2 - 1);
                }
            }
            ans = temp;
        }
        return ans;
    }

    public static ArrayList<Integer> beautifulArray_DivideNConquer(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        divideConquer(1, 1, res, n);
        return res;
    }

    private static void divideConquer(int start, int increment, ArrayList<Integer> res, int n) {
        if (start + increment > n) {
            res.add(start);
            return;
        }
        divideConquer(start, 2 * increment, res, n);
        divideConquer(start + increment, 2 * increment, res, n);
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        // list.add(1);
        // list.add(100);
        // list.add(200);
        // list.add(1);
        // list.add(100);
        // list.add(10);
        // list.add(7);
        // list.add(8);
        // list.add(9);
        list = beautifulArray_DivideNConquer(5);
        printArrayList(list);
    }
}
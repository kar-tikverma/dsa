import java.util.*;
public class _3_Arrays {

    public static int linearSearch(int arr[], int key) {
        for(int i=0;i<arr.length;i++) {
            if(arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public static int largestElement(int arr[]) {
        int largest = Integer.MIN_VALUE; // = -2147483648
        for(int i=0;i<arr.length;i++) {
            if(arr[i] > largest) {
                largest = arr[i];
            }
        }
        return largest;
    }

    public static int smallestElement(int arr[]) {
        int smallest = Integer.MAX_VALUE; // = 2147483647
        for(int i=0;i<arr.length;i++) {
            if(arr[i] < smallest) {
                smallest = arr[i];
            }
        }
        return smallest;
    }

    public static int binarySearch(int arr[], int key) {
        int index = -1;
        int start = 0, end = arr.length - 1;
        int mid = (start + end) / 2;
        while(start <= end) {
            if(arr[mid] == key) {
                index = mid;
                for(int i = mid; i >= start; i--) {
                    if(arr[i] == key) {
                        index = i;
                    }
                    else {
                        return index;
                    }
                }
                return index;
            }
            else if(arr[mid] > key) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
            mid = (start + end) / 2;
        }
        return index;
    }

    public static void reverse(int arr[]) {
        for(int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void pairs(int arr[]) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                System.out.println("{" + arr[i] + ", " + arr[j] + "}");
            }
        }
    }

    public static void printSubArrays(int arr[]) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = i; j < arr.length; j++) {
                System.out.print("{");
                int k;
                for(k = i; k < j; k++) {
                    System.out.print(arr[k] + ", ");
                }
                System.out.print(arr[k]);
                System.out.print("}");
                System.out.println();
            }
        }
    }

    public static int maxSubarraySum_BruteForce(int arr[]) {
        int maxSum = Integer.MIN_VALUE;
        int sum;
        for(int i = 0; i < arr.length; i++) {
            for(int j = i; j < arr.length; j++) {
                sum = 0;
                for(int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                if(sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    public static int maxSubarraySum_PrefixSum(int arr[]) {
        int n = arr.length;
        int prefixArr[] = new int[n];
        int maxSum = prefixArr[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefixArr[i] = prefixArr[i-1] + arr[i];
            if (prefixArr[i] > maxSum) {
                maxSum = prefixArr[i];
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = prefixArr[j] - prefixArr[i-1];
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }

        return maxSum;
    }

    public static int maxSubarraySum_KadanesAlgorithm (int arr[]) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum > maxSum) {
                maxSum = sum;
            } else if (sum < 0) {
                sum = 0;
            }
        }

        return maxSum;
    }

    public static int trappingRainwater(int barHeight[]) {
        int len = barHeight.length;
        if(len < 3) {
            return 0;
        }
        int volume = 0;
        /*
        // My Solution -> Time Complexity = O(n**2)
        for(int init = 0; init < len - 2;) {
            for(int j = 1; j < len; j++) {
                if(barHeight[j] >= barHeight[init]) {
                    init = j;
                }
                else {
                    break;
                }
            }
            if(init >= len - 1) {
                return volume;
            }
            int fnl = init + 1;
            for(int j = init + 2; j < len; j++) {
                if(barHeight[j] >= barHeight[init]) {
                    fnl = j;
                    break;
                }
                else {
                    if(barHeight[j] > barHeight[fnl]) {
                        fnl = j;
                    }
                }
            }
            if(fnl != init + 1) {
                int min = Math.min(barHeight[init], barHeight[fnl]);
                for(int j = init + 1; j <= fnl - 1; j++) {
                    volume += (min - barHeight[j]);
                }
            }
            init = fnl;
        }
        */
        
        // Apna College Solution (Most Optimized) -> Time Complexity = O(n)
        int leftMaxBound[] = new int[len]; // Auxiliary Array
        int rightMaxBound[] = new int[len]; // Auxiliary Array
        leftMaxBound[0] = barHeight[0];
        rightMaxBound[len - 1] = barHeight[len - 1];
        for(int i = 1, j = len - 2; i < len; i++, j--) {
            leftMaxBound[i] = Math.max(leftMaxBound[i-1], barHeight[i]);
            rightMaxBound[j] = Math.max(rightMaxBound[j+1], barHeight[j]);
        }
        for(int i = 0; i < len; i++) {
            // Water Level = min(left max boundary, right max boundary)
            // Trapped Water = Water Level - Bar Height
            volume += (Math.min(leftMaxBound[i], rightMaxBound[i]) - barHeight[i]);
        }
        return volume;
    }

    public static int StockTrade_forBestPrice(int prices[]) {
        int maxProfit = 0;
        int bp = prices[0];
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < bp) {
                bp = prices[i];
            }
            else {
                maxProfit = Math.max(maxProfit, prices[i] - bp);
            }
        }
        return maxProfit;
    }

    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<nums.length; i++) {
            if(set.contains(nums[i])) {
                return true;
            }
            else {
                set.add(nums[i]);
            }
        }
        return false;
    }

    public static int ArraysAssignment_Q2(int[] nums, int target) {
        int min = minSearch(nums);
        if(nums[min] <= target && target <= nums[nums.length-1]){
            return binarySearch(nums,min,nums.length-1,target);
        }
        else{
            return binarySearch(nums,0,min,target);
        }
    }

    public static int binarySearch(int[] nums,int start,int end,int target){
        while(start <= end){
            int mid = (start + end) / 2;
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] > target){
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static int minSearch(int[] nums){
        int left = 0;
        int right = nums.length-1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(mid > 0  && nums[mid-1] > nums[mid]){
                return mid;
            }
            else if(nums[left] <= nums[mid] && nums[mid] > nums[right]){
                left = mid+1;
            }
            else{
                right = mid-1;
            }
        }
        return left;
    }

    public static List<List<Integer>> ArraysAssignment_Q5(int nums[]) {
        List<List<Integer>> result = new ArrayList <List<Integer>> ();
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                for(int k = j + 1; k < nums.length; k++) {
                    if(nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = new ArrayList < Integer > ();
                        triplet.add(nums[i]);
                        triplet.add(nums[j]);
                        triplet.add(nums[k]);
                        Collections.sort(triplet);
                        result.add(triplet);
                    }
                }
            }
        }
        result = new ArrayList<List<Integer>> (new LinkedHashSet<List<Integer>> (result));
        return result;
    }
    
    public static void main(String args[]) {
        int arr[] = {-1, 0, 1, 2, -1, -4};
        System.out.println(ArraysAssignment_Q5(arr));
    }
}
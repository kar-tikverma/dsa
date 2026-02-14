import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class _04_SortingAlgorithms {
    
    public static void bubbleSort(int arr[]) { // TC -> O(n^2)
        for(int pass = 0; pass < arr.length - 1; pass++) {
            int swaps = 0;
            for(int j = 0; j < arr.length - pass - 1; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swaps++;
                }
            }
            if(swaps == 0) {
                break;
            }
        }
    }

    public static void selectionSort (List<Integer> nums) { // O(n^2)
        for (int i = 0; i < nums.size()-1; i++) {
            int min = i;
            for (int j = i; j < nums.size(); j++) {
                if (nums.get(j) < nums.get(min)) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = nums.get(min);
                nums.set(min, nums.get(i));
                nums.set(i, temp);
            }
        }
    }

    public static void insertionSort(int arr[]) { // TC -> O(n^2)
        for(int pass = 1; pass < arr.length; pass++) {
            int curr = arr[pass];
            int prev = pass - 1;
            while(prev >= 0 && arr[prev] > curr) {
                arr[prev + 1] = arr[prev--];
            }
            arr[prev + 1] = curr;
        }
    }

    public static void inbuiltSort(int arr[]) {
        Arrays.sort(arr); // Arrays.sort(array[], fromIndex, toIndex)
    }
    public static void inbuiltSort_Reverse(Integer arr[]) {
        Arrays.sort(arr,Collections.reverseOrder());
        // Arrays.sort(array[], fromIndex, toIndex, Collections.reverseOrder());
    }

    public static void countingSort(int arr[]) {
        int largest = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            largest = Math.max(largest, arr[i]);
        }
        int count[] = new int[largest + 1];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; count[i] > 0; j++) {
                arr[j] = i;
                count[i]--;
            }
        }
    }

    public static void printArray(int arr[]) {
        int i;
        System.out.print("{");
        for(i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print(arr[i] + "}");
    }
    public static void printArray(Integer arr[]) {
        int i;
        System.out.print("{");
        for(i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print(arr[i] + "}");
    }
    
    public static void main(String args[]) {
        int arr[] = {1,4,1,3,2,4,3,7};
        countingSort(arr);
        printArray(arr);
    }
}
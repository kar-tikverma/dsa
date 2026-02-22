public class _11_Sorting_usingRecursion {
    public static void printArray(int[] arr, int i) {
        if (i == arr.length) {
            return;
        }
        System.out.print(arr[i] + " ");
        printArray(arr, i + 1);
    }

    public static void printArray(String[] arr, int i) {
        if (i == arr.length) {
            return;
        }
        System.out.print(arr[i] + " ");
        printArray(arr, i + 1);
    }

    public static void mergeSort(int[] arr) { // Time Complexity -> O(n*log(2)n), Space -> O(n)
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int si, int ei) {
        if (si == ei) {
            return;
        }
        int mid = si + (ei - si) / 2;
        mergeSort(arr, si, mid);
        mergeSort(arr, mid + 1, ei);
        merge(arr, si, mid, ei);
    }

    private static void merge(int[] arr, int si, int mid, int ei) {
        int[] tempArr = new int[ei - si + 1];
        int i = si; // iterator for left part
        int j = mid + 1; // iterator for right part
        int k = 0; // iterator for tempArr
        while (i <= mid && j <= ei) {
            if (arr[i] <= arr[j]) {
                tempArr[k++] = arr[i++];
            } else {
                tempArr[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            tempArr[k++] = arr[i++];
        }
        while (j <= ei) {
            tempArr[k++] = arr[j++];
        }
        for (i = si, k = 0; i <= ei; i++, k++) {
            arr[i] = tempArr[k];
        }
    }

    public static void quickSort(int[] arr, int si, int ei) {
        if (si >= ei) {
            return;
        }
        int pivot = ei;
        boolean swapped = true;
        int i = 0, j = pivot - 1;
        while (swapped) {
            swapped = false;
            for (int x = i; x < pivot; x++) {
                if (arr[x] > arr[pivot]) {
                    // swap
                    int temp = arr[pivot];
                    arr[pivot] = arr[x];
                    arr[x] = temp;
                    pivot = x;
                    i = pivot + 1;
                    swapped = true;
                    break;
                }
            }
            for (int x = j; x > pivot; x--) {
                if (arr[x] < arr[pivot]) {
                    // swap
                    int temp = arr[pivot];
                    arr[pivot] = arr[x];
                    arr[x] = temp;
                    pivot = x;
                    j = pivot - 1;
                    swapped = true;
                    break;
                }
            }
        }
        quickSort(arr, si, pivot - 1);
        quickSort(arr, pivot + 1, ei);
    }

    public static int search_SortedAndRotatedArray(int[] arr, int target, int si, int ei) {
        if (si > ei) {
            return -1;
        }
        int mid = si + (ei - si) / 2;
        if (arr[mid] == target) {
            while (mid >= si) {
                mid -= 1;
                if (arr[mid] != target) {
                    return mid + 1;
                }
            }
            return mid + 1;
        }
        if (arr[si] < arr[mid]) {
            if (target < arr[mid] && target >= arr[si]) {
                return search_SortedAndRotatedArray(arr, target, si, mid - 1);
            } else {
                return search_SortedAndRotatedArray(arr, target, mid + 1, ei);
            }
        } else {
            if (target > arr[mid] && target <= arr[ei]) {
                return search_SortedAndRotatedArray(arr, target, mid + 1, ei);
            } else {
                return search_SortedAndRotatedArray(arr, target, si, mid - 1);
            }
        }
    }

    public static void mergeSort_Strings(String[] arr, int si, int ei) {
        if (si == ei) {
            return;
        }
        int mid = si + (ei - si) / 2;
        mergeSort_Strings(arr, si, mid);
        mergeSort_Strings(arr, mid + 1, ei);
        merge_Str(arr, si, ei);
    }

    public static void merge_Str(String[] arr, int si, int ei) {
        String[] tempArr = new String[ei - si + 1];
        int i = si;
        int mid = si + (ei - si) / 2;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= ei) {
            if (arr[i].compareTo(arr[j]) < 0) {
                tempArr[k++] = arr[i++];
            } else {
                tempArr[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            tempArr[k++] = arr[i++];
        }
        while (j <= ei) {
            tempArr[k++] = arr[j++];
        }
        k = 0;
        for (int x = si; x <= ei; x++) {
            arr[x] = tempArr[k++];
        }
    }

    static int uni = 1;

    public static int InversionCount(int[] arr, int si, int ei) {
        if (si >= ei) {
            return 0;
        }
        int pivot = ei;
        int i = si;
        int j = pivot - 1;
        int swaps = 0;
        int last_swaps = -1;
        while (swaps > last_swaps) {
            last_swaps = swaps;
            for (int x = i; x < pivot; x++) {
                if (arr[x] > arr[pivot]) {
                    // swap
                    int temp = arr[x];
                    arr[x] = arr[pivot];
                    arr[pivot] = temp;
                    pivot = x;
                    i = pivot + 1;
                    swaps++;
                    break;
                }
            }
            for (int x = j; x > pivot; x--) {
                if (arr[x] < arr[pivot]) {
                    // swap
                    int temp = arr[x];
                    arr[x] = arr[pivot];
                    arr[pivot] = temp;
                    pivot = x;
                    j = pivot - 1;
                    swaps++;
                    break;
                }
            }
        }
        return swaps + InversionCount(arr, si, pivot - 1) + InversionCount(arr, pivot + 1, ei);
    }

    public static void main(String[] args) {
        int[] arr = { 2, 4, 1, 3, 5 };
        System.out.println(InversionCount(arr, 0, arr.length - 3));
    }
}
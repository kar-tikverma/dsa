public class _10_RecursionBasics {
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static int sumOf_n(int n) {
        if (n == 1) {
            return 1;
        }
        return n + sumOf_n(n - 1);
    }

    public static int nth_fibonacciNumber(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return nth_fibonacciNumber(n - 1) + nth_fibonacciNumber(n - 2);
    }

    public static int nth_fibonacciNumber_direct(int n) {
        double phi = (1 + Math.sqrt(5)) / 2;
        return (int) Math.round(Math.pow(phi, n) / Math.sqrt(5));
    }

    public static boolean isSorted(int[] arr, int len) {
        if (len == 1) {
            return true;
        }
        return arr[len - 1] >= arr[len - 2] && isSorted(arr, len - 1);
    }

    public static int firstOccurence(int[] arr, int key, int startIndex) {
        if (startIndex == arr.length) {
            return -1;
        }
        if (arr[startIndex] == key) {
            return startIndex;
        }
        return firstOccurence(arr, key, startIndex + 1);
    }

    public static int lastOccurence(int[] arr, int key, int startIndex) {
        if (startIndex == arr.length) {
            return -1;
        }
        int isFound = lastOccurence(arr, key, startIndex + 1);
        if (isFound == -1 && arr[startIndex] == key) {
            return startIndex;
        }
        return isFound;
    }

    public static int calcPow(int x, int n) { // Time Complexity -> O(n)
        if (n == 0) {
            return 1;
        }
        return x * calcPow(x, n - 1);
    }

    public static int calcPow2(int x, int n) { // Time Complexity -> O(n)
        if (n == 0) {
            return 1;
        }
        int result = calcPow2(x, n / 2) * calcPow2(x, n / 2);
        if ((n & 1) == 1) {
            result *= x;
        }
        return result;
    }

    public static int calcPow_Optimized(int x, int n) { // Time Complexity -> O(log(2)n)
        if (n == 0) {
            return 1;
        }
        int result = calcPow_Optimized(x, n / 2);
        result *= result;
        if ((n & 1) == 1) {
            result *= x;
        }
        return result;
    }

    public static int tilingWays(int n) {
        // floor size = 2 x n

        // base case
        if (n == 0 || n == 1) {
            return 1;
        }

        // vertical choice
        int fnm1 = tilingWays(n - 1);

        // horizontal choice
        int fnm2 = tilingWays(n - 2);

        int totalWays = fnm1 + fnm2;

        return totalWays;
    }

    public static String removeDuplicates(String str, int i, boolean[] map) {
        // Condition given: Original String only contains lowercase letters
        if (i == str.length()) {
            return "";
        }
        StringBuilder strb = new StringBuilder("");
        int mapPos = str.charAt(i) - 'a';
        if (map[mapPos] == false) {
            map[mapPos] = true;
            strb.append(str.charAt(i));
        }
        strb.append(removeDuplicates(str, i + 1, map));
        return strb.toString();
    }

    public static int friendsPairingWays(int n) {
        // n = number of friends
        if (n == 1 || n == 2) {
            return n;
        }
        // choice = single for nth friend
        int fnm1 = friendsPairingWays(n - 1);
        int fnm2 = friendsPairingWays(n - 2);
        return fnm1 + (n - 1) * fnm2;
    }

    public static void numberOfBinaryStrings(int size, String str, int lastPlace) {
        if (size == 0) {
            System.out.println(str);
            return;
        }
        numberOfBinaryStrings(size - 1, str + "0", 0);
        if (lastPlace == 0) {
            numberOfBinaryStrings(size - 1, str + "1", 1);
        }
    }

    public static void allOccurences(int[] arr, int i, int key) {
        if (i == arr.length) {
            return;
        }
        if (arr[i] == key) {
            System.out.print(i + " ");
        }
        allOccurences(arr, i + 1, key);
    }

    static String[] numbers = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

    public static String convertToString(int n) {
        if (n == 0) {
            return "";
        }
        int lastDigit = n % 10;
        StringBuilder res = new StringBuilder("");
        res.append(convertToString(n / 10));
        res.append(numbers[lastDigit] + " ");
        return res.toString();
    }

    public static int lengthOfString(String str) {
        if (str.length() == 0) {
            return 0;
        }
        return lengthOfString(str.substring(1)) + 1;
    }

    public static int countSubstrs_EndingWithSameCharacter(String str, int si, int ei, int len) {
        if (len == 1) {
            return 1;
        }
        if (len <= 0) {
            return 0;
        }
        int res = countSubstrs_EndingWithSameCharacter(str, si + 1, ei, len - 1)
                + countSubstrs_EndingWithSameCharacter(str, si, ei - 1, len - 1)
                - countSubstrs_EndingWithSameCharacter(str, si + 1, ei - 1, len - 2);
        if (str.charAt(si) == str.charAt(ei)) {
            res++;
        }
        return res;
    }

    public static int towersOfHanoi_noOfSteps(int n) {
        if (n == 1) {
            return 1;
        }
        return 1 + 2 * towersOfHanoi_noOfSteps(n - 1);
    }

    public static void towersOfHanoi_Steps(int n, String src, String aux, String dest) {
        if (n == 1) {
            System.out.println("Transfer disk 1 from " + src + " to " + dest);
            return;
        }
        towersOfHanoi_Steps(n - 1, src, dest, aux);
        System.out.println("Transfer disk " + n + " from " + src + " to " + dest);
        towersOfHanoi_Steps(n - 1, aux, src, dest);
    }

    public static void main(String[] args) {
        System.out.println(nth_fibonacciNumber_direct(16));
        ;
    }
}
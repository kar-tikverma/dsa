public class _05_2DArrays {
    public static void printMatrix(int arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void SpiralMatrix(int arr[][]) {
        int colLen = arr.length;
        int rowLen = arr[0].length;
        int startRow = 0, startCol = 0, endRow = colLen - 1, endCol = rowLen - 1;
        while (startRow <= endRow && startCol <= endCol) {
            // print start row
            for (int i = startCol; i <= endCol; i++) {
                System.out.print(arr[startRow][i] + " ");
            }
            // print end column
            for (int i = startRow + 1; i <= endRow; i++) {
                System.out.print(arr[i][endCol] + " ");
            }
            // print end row
            for (int i = endCol - 1; i >= startCol; i--) {
                if (startRow == endRow) {
                    return;
                }
                System.out.print(arr[endRow][i] + " ");
            }
            // print start column
            for (int i = endRow - 1; i > startRow; i--) {
                if (startCol == endCol) {
                    return;
                }
                System.out.print(arr[i][startCol] + " ");
            }
            startRow++;
            startCol++;
            endCol--;
            endRow--;
        }
    }

    public static int DiagonalSum_Primary(int arr[][]) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i][i];
        }
        return sum;
    }

    public static int DiagonalSum_Secondary(int arr[][]) {
        int sum = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            sum += arr[i][len - i - 1];
        }
        return sum;
    }

    public static int DiagonalSum_P_S(int arr[][]) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            // Primary Diagonal
            sum += arr[i][i];
            if (i != arr.length - i - 1) {
                sum += arr[i][arr.length - i - 1];
            }
        }
        return sum;
    }

    public static void StaircaseSearch(int arr[][], int key) { // Time Complexity -> O(n + m)
        int colLen = arr.length, rowLen = arr[0].length;
        int i = 0, j = rowLen - 1;
        while (i < colLen && j >= 0) {
            if (arr[i][j] == key) {
                System.out.println(key + " is present at indices (" + i + ", " + j + ")");
                return;
            } else if (arr[i][j] > key) {
                j--;
            } else {
                i++;
            }
        }
        System.out.println(key + " not found!");
    }

    public static int[][] Transpose(int arr[][]) {
        int t[][] = new int[arr[0].length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                t[j][i] = arr[i][j];
            }
        }
        return t;
    }

    public static void main(String args[]) {
        int arr[][] = { { 10, 20, 30, 40 },
                { 15, 25, 35, 45 },
                { 27, 29, 37, 48 },
                { 32, 33, 39, 50 } };
        printMatrix(arr);
        System.out.println();
        int trans[][] = Transpose(arr);
        printMatrix(trans);
    }
}
public class _12_Backtracking {

    public static void findSubsets (String str, String ans, int i) { // Time Complexity -> O(n * 2^n)
        if (i == str.length()) {
            if(ans.length() == 0) {
                System.out.println("null");
                return;
            }
            System.out.println(ans);
            return;
        }

        //Choice: Yes
        findSubsets(str, ans+str.charAt(i), i+1);
        //Choice: No
        findSubsets(str, ans, i+1);
    }

    public static void findPermutations (String str, String ans) { // Time Complexity -> O(n * n!)
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }
        for(int i = 0; i < str.length(); i++) {
            String newStr = str.substring(0, i) + str.substring(i+1);
            findPermutations(newStr, ans+str.charAt(i));
        }
    }

    public static void nQueens_allSolutions (int row, char[][] chessBoard) {
        if (row == chessBoard.length) {
            printChessBoard(chessBoard);
            return;
        }
        for (int i = 0; i < chessBoard.length; i++) {
            if(isSafe(chessBoard, row, i)) {
                chessBoard[row][i] = 'Q';
                nQueens_allSolutions (row+1, chessBoard);
                chessBoard[row][i] = 'x';
            }
        }
    }

    public static void nQueens_singleSoln (char[][] chessBoard) {
        for(int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                chessBoard[i][j] = 'x';
            }
        }
        if (nQueens_SS_Aux(0, chessBoard)) {
            printChessBoard(chessBoard);
        }
        else {
            System.out.println("Can't have " + chessBoard.length + " Queens on the board.");
        }
    }
    private static boolean nQueens_SS_Aux (int row, char[][] chessBoard) {
        if (row == chessBoard.length) {
            return true;
        }
        for (int i = 0; i < chessBoard.length; i++) {
            if(isSafe(chessBoard, row, i)) {
                chessBoard[row][i] = 'Q';
                if(nQueens_SS_Aux (row+1, chessBoard)) {
                    return true;
                }
                chessBoard[row][i] = 'x';
            }
        }
        return false;
    }
    private static boolean isSafe (char[][] chessBoard, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (chessBoard[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < chessBoard.length; i--, j++) {
            if (chessBoard[i][j] == 'Q') {
                return false;
            }
        }
        
        return true;
    }
    private static void printChessBoard (char[][] chessBoard) {
        System.out.println("-----Chess Board-----");
        for(int i = 0; i < chessBoard.length; i++) {
            for(int j = 0; j < chessBoard.length; j++) {
                System.out.print(chessBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int gridWays (int[][] grid, int row, int col) { // Time Complexity -> O(2^(m+n))
        if(row == grid.length-1 && col == grid[0].length-1) {
            return 1;
        }
        if((row == grid.length) || (col == grid[0].length)) {
            return 0;
        }
        return gridWays(grid, row+1, col) + gridWays(grid, row, col+1);
    }
    public static long gridWays (int[][] grid) {
        int n_col = grid[0].length;
        int n_row = grid.length;
        // int total_turns = n_col + n_row - 2;
        // int max = Math.max(n_col, n_row);
        int min = Math.min(n_col, n_row);
        // long num = 1;
        // for(int i = max; i <= total_turns; i++) {
        //     num *= i;
        // }
        long denom = 1;
        for(int i = 2; i < min; i++) {
            denom *= i;
        }
        
        return denom;
    }

    public static boolean completeSudoku (int[][] sudoku, int row, int col) {
        /*
        Analyse properly for best base case.
        The two base case conditions that can be used:
            row == sudoku.length && col == 0
            row == sudoku.length
        */
        /*
        Sample Example of Sudoku:
        int[][] sud = {
            {0,0,8,0,0,0,0,0,0},
            {4,9,0,1,5,7,0,0,2},
            {0,0,3,0,0,4,1,9,0},
            {1,8,5,0,6,0,0,2,0},
            {0,0,0,0,2,0,0,6,0},
            {9,6,0,4,0,5,3,0,0},
            {0,3,0,0,7,2,0,0,4},
            {0,4,9,0,3,0,0,5,7},
            {8,2,7,0,0,9,0,1,3}};
        */
        if (row == sudoku.length && col == 0) {
            return true;
        }
        int nextRow = row, nextCol = col + 1;
        if(nextCol == 9) {
            nextRow = row + 1;
            nextCol = 0;
        }
        if (sudoku[row][col] != 0) {
            return completeSudoku(sudoku, nextRow, nextCol);
        }
        for (int digit = 1; digit <= 9; digit++) {
            if (isUnique_inSudoku(sudoku, row, col, digit)) {
                sudoku[row][col] = digit;
                if (completeSudoku(sudoku, nextRow, nextCol)) {
                    return true;
                }
                sudoku[row][col] = 0;
            }
        }
        return false;
    }
    private static boolean isUnique_inSudoku (int[][] sudoku, int row, int col, int testDigit) {
        //row
        for(int i = 0; i < sudoku[0].length; i++) {
            if(sudoku[row][i] == testDigit) {
                return false;
            }
        }
        //col
        for(int i = 0; i < sudoku.length; i++) {
            if (sudoku[i][col] == testDigit) {
                return false;
            }
        }
        //3x3 grid
        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;
        for (int i = startRow; i < startRow+3; i++) {
            for (int j = startCol; j < startCol+3; j++) {
                if (sudoku[i][j] == testDigit) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void printSudoku (int[][] sudoku) {
        System.out.println("-----Sudoku-----");
        for(int i = 0; i < sudoku.length; i++) {
            for(int j = 0; j < sudoku[0].length; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int numberOfPaths (int[][] maze) {
        return numberOfPaths_Aux(maze, 0, 0);
    }
    private static int numberOfPaths_Aux (int[][] maze, int row, int col) {
        if (row == maze.length-1 && col == maze[0].length-1) {
            return 1;
        }
        if (row == maze.length || col == maze[0].length) {
            return 0;
        }
        int paths = 0;
        if (row < maze.length-1 && maze[row+1][col] == 1) {
            paths += numberOfPaths_Aux (maze, row+1, col);
        }
        if (col < maze[0].length-1 && maze[row][col+1] == 1) {
            paths += numberOfPaths_Aux (maze, row, col+1);
        }
        return paths;
    }

    public static void printAllPaths (int[][] maze) {
        int[][] path = new int[maze.length][maze[0].length];
        path[0][0] = 1; //starting position
        path[maze.length-1][maze[0].length-1] = 1; // ending position
        printAllPaths_Aux(maze, 0, 0, path);
    }
    private static void printAllPaths_Aux (int[][] maze, int row, int col, int[][] path) {
        if (row == maze.length-1 && col == maze[0].length-1) {
            printPath(path);
            return;
        }
        if (row == maze.length || col == maze[0].length) {
            return;
        }
        if (row < maze.length-1 && maze[row+1][col] == 1) {
            path[row+1][col] = 1;
            printAllPaths_Aux(maze, row+1, col, path);
            path[row+1][col] = 0;
        }
        if (col < maze[0].length-1 && maze[row][col+1] == 1) {
            path[row][col+1] = 1;
            printAllPaths_Aux(maze, row, col+1, path);
            path[row][col+1] = 0;
        }
    }
    private static void printPath (int[][] path) {
        System.out.println("----- Path -----");
        for(int i = 0; i < path.length; i++) {
            for(int j = 0; j < path.length; j++) {
                System.out.print(" " + path[i][j] + " ");
            }
            System.out.println();
        }
    }

    static char[][] tel_char = { {'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},
                        {'p','q','r','s'},{'t','u','v'},{'w','x','y','z'} };
    
    public static void letterCombn (String nums) {
        StringBuilder sb = new StringBuilder("");
        letterCombn_Aux(nums, 0, sb);
    }
    private static void letterCombn_Aux (String nums, int idx, StringBuilder letters) {
        if (idx == nums.length()) {
            System.out.println(letters);
            return;
        }
        for (int i = 0; i < tel_char[nums.charAt(idx)-50].length; i++) {
            letters.append(tel_char[nums.charAt(idx)-50][i]);
            letterCombn_Aux (nums, idx+1, letters);
            letters.deleteCharAt(idx);
        }
    }

    public static void knightMoves (int n) {
        int[][] chessBoard = new int [n][n];
        boolean ans = false;
        for (int i = 0; i <= (chessBoard.length-1)/2; i++) {
            for (int j = 0; j <= (chessBoard.length-1)/2; j++) {
                if (knightMoves_Aux(chessBoard, i, j, 1)) {
                    ans = true;
                    break;
                }
            }
            if (ans == true) {
                break;
            }
        }
        if (ans == false) {
            System.out.println("The knight cannot traverse the whole board");
        }
    }
    static int[] r = {1, 2, 2, 1, -1, -2,-2,-1};
    static int[] c = {2, 1,-1,-2, -2, -1, 1, 2};
    private static boolean knightMoves_Aux (int[][] chessBoard, int row, int col, int move) {
        if (move > chessBoard.length*chessBoard.length) {
            printChessBoard(chessBoard);
            return true;
        }
        if (row >= chessBoard.length || col >= chessBoard[0].length || row < 0 || col < 0) {
            return false;
        }
        if (chessBoard[row][col] == 0){
            chessBoard[row][col] = move;
        }
        else {
            return false;
        }
        for(int i = 0; i < 8; i++) {
            if(knightMoves_Aux(chessBoard, row + r[i], col + c[i], move+1)){
                return true;
            }
        }
        chessBoard[row][col] = 0;
        return false;
    }
    private static void printChessBoard (int[][] chessBoard) {
        System.out.println();
        System.out.println("----------  Chess Board  ----------");
        System.out.println();
        for(int i = 0; i < chessBoard.length; i++) {
            for(int j = 0; j < chessBoard.length; j++) {
                System.out.print(chessBoard[i][j] + "\t");
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void main (String[] args) {
        //
    }
}
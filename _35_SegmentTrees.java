public class _35_SegmentTrees {

    private int[] arr;
    private int[] sumTree;
    private int[] maxTree;
    private int[] minTree;

    // Sum
    public int getSum (int qi, int qj) { // O(log2(n))
        int n = arr.length;
        // sumTree = new int[4*n];
        // size = 2 * 2^ceil(log2(n)) - 1
        sumTree = new int[(int) (2 * Math.pow(2, Math.ceil(Math.log(n)/Math.log(2))) - 1)];
        buildSumTree (0, 0, n-1);

        return getSum_Util(qi, qj, 0, arr.length-1, 0);
    }
    private int getSum_Util (int qi, int qj, int si, int sj, int curr) {
        if (si > qj || sj < qi) { // No Overlap
            return 0;
        }
        if (si >= qi && sj <= qj) { // Complete Overlap
            return sumTree[curr];
        }
        
        // Partial Overlap
        int mid = (sj - si) / 2 + si;
        return getSum_Util(qi, qj, si, mid, 2*curr + 1) + getSum_Util(qi, qj, mid+1, sj, 2*curr + 2);
    }
    private int buildSumTree (int curr, int si, int sj) {
        if (si == sj) {
            return sumTree[curr] = arr[si];
        }

        int mid = (sj - si) / 2 + si;
        sumTree[curr] = buildSumTree(2*curr + 1, si, mid)
                                + buildSumTree(2*curr + 2, mid+1, sj);
        
        return sumTree[curr];
    }

    // Maximum
    public int max (int qi, int qj) {
        int n = arr.length;
        maxTree = new int[(int)(2 * Math.pow(2, Math.ceil(Math.log(n)/Math.log(2))) - 1)];
        buildMaxTree(qi, qj, 0, n-1, 0);

        return maxUtil(qi, qj, 0, n-1, 0);
    }
    private int maxUtil (int qi, int qj, int si, int sj, int curr) {
        if (si > qj || sj < qi) { // No Overlap
            return Integer.MIN_VALUE;
        }
        if (si >= qi && sj <= qj) { // Complete Overlap
            return maxTree[curr];
        }

        // Partial Overlap
        int mid = (sj - si) / 2 + si;
        int left = maxUtil(qi, qj, si, mid, 2*curr + 1);
        int right = maxUtil(qi, qj, mid+1, sj, 2*curr + 2);

        return Math.max(left, right);
    }
    private void buildMaxTree (int qi, int qj, int si, int sj, int curr) {
        if (si == sj) {
            maxTree[curr] = arr[si];
            return;
        }

        int mid = (sj - si) / 2 + si;
        buildMaxTree(qi, qj, si, mid, 2*curr + 1);
        buildMaxTree(qi, qj, mid+1, sj, 2*curr + 2);

        maxTree[curr] = Math.max(maxTree[2*curr + 1], maxTree[2*curr + 2]);
    }

    // Minimum
    public int min (int qi, int qj) {
        int n = arr.length;
        minTree = new int[(int)(2 * Math.pow(2, Math.ceil(Math.log(n)/Math.log(2))) - 1)];
        buildMinTree(qi, qj, 0, n-1, 0);

        return minUtil(qi, qj, 0, n-1, 0);
    }
    private int minUtil (int qi, int qj, int si, int sj, int curr) {
        if (si > qj || sj < qi) { // No Overlap
            return Integer.MAX_VALUE;
        }
        if (si >= qi && sj <= qj) { // Complete Overlap
            return minTree[curr];
        }

        // Partial Overlap
        int mid = (sj - si) / 2 + si;
        int left = minUtil(qi, qj, si, mid, 2*curr + 1);
        int right = minUtil(qi, qj, mid+1, sj, 2*curr + 2);

        return Math.min(left, right);
    }
    private void buildMinTree (int qi, int qj, int si, int sj, int curr) {
        if (si == sj) {
            minTree[curr] = arr[si];
            return;
        }

        int mid = (sj - si) / 2 + si;
        buildMinTree(qi, qj, si, mid, 2*curr + 1);
        buildMinTree(qi, qj, mid+1, sj, 2*curr + 2);

        minTree[curr] = Math.min(minTree[2*curr + 1], minTree[2*curr + 2]);
    }

    // Update
    public void updateArray (int idx, int newVal) {
        int diff = newVal - arr[idx];
        arr[idx] = newVal;

        if (sumTree != null) {
            updateSumTree(idx, diff, 0, 0, arr.length-1);
        }
        if (maxTree != null) {
            updateMaxTree(idx, newVal, 0, 0, arr.length-1);
        }
        if (minTree != null) {
            updateMinTree(idx, newVal, 0, 0, arr.length-1);
        }
    }
    private void updateSumTree (int idx, int diff, int curr, int si, int sj) { // O(log2(n))
        if (idx < si || idx > sj) {
            return;
        }

        sumTree[curr] += diff;

        if (si == sj) {
            return;
        }

        int mid = si + (sj - si) / 2;
        updateSumTree(idx, diff, 2*curr + 1, si, mid);
        updateSumTree(idx, diff, 2*curr + 2, mid+1, sj);
    }
    private void updateMaxTree (int idx, int newVal, int curr, int si, int sj) {
        if (si > idx || sj < idx) {
            return;
        }
        if (si == sj) {
            maxTree[curr] = newVal;
            return;
        }

        int mid = si + (sj - si) / 2;
        updateMaxTree(idx, newVal, 2*curr + 1, si, mid);
        updateMaxTree(idx, newVal, 2*curr + 2, mid+1, sj);

        maxTree[curr] = Math.max(maxTree[2*curr + 1], maxTree[2*curr + 2]);
    }
    private void updateMinTree (int idx, int newVal, int curr, int si, int sj) {
        if (si > idx || sj < idx) {
            return;
        }
        if (si == sj) {
            minTree[curr] = newVal;
            return;
        }

        int mid = si + (sj - si) / 2;
        updateMinTree(idx, newVal, 2*curr + 1, si, mid);
        updateMinTree(idx, newVal, 2*curr + 2, mid+1, sj);

        minTree[curr] = Math.min(minTree[2*curr + 1], minTree[2*curr + 2]);
    }

    public static void main (String[] args) {
        int[] a = {1,2,3,4,5,6};
        _35_SegmentTrees ST = new _35_SegmentTrees();
        ST.arr = a;
        System.out.println(ST.getSum(2, 5));
        print_1D(ST.sumTree);
    }

    private static void print_1D (int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }
}
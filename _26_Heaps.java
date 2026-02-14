import java.util.*;
public class _26_Heaps {

    static void heapSort_asc (int[] heap) {
        int len = heap.length;
        //Step 1: Create MaxHeap
        for (int i = len/2 - 1; i >= 0; i--) {
            heapify_maxHeap(heap, i, len);
        }

        // Step 2: Push largest at end
        for (int i = len-1; i > 0; i--) {
            int temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;

            heapify_maxHeap(heap, 0, i);
        }
    }
    private static boolean heapify_maxHeap (int[] heap, int root, int size) { // TC -> O(log2n)
        int left = 2*root + 1;
        int right = left + 1;
        int maxIdx = root;
        
        if (left < size && heap[left] > heap[maxIdx]) {
            maxIdx = left;
        }
        if (right < size && heap[right] > heap[maxIdx]) {
            maxIdx = right;
        }

        if (maxIdx != root) {
            int temp = heap[root];
            heap[root] = heap[maxIdx];
            heap[maxIdx] = temp;

            heapify_maxHeap (heap, maxIdx, size);
            return true;
        }

        return false;
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;
        int distSq;
        int idx;

        Point (int x, int y, int distSq, int idx) {
            this.x = x;
            this.y = y;
            this.distSq = distSq;
            this.idx = idx;
        }

        @Override
        public int compareTo (Point p2) {
            return this.distSq - p2.distSq;
        }
    }
    static void K_nearbyCars (int[][] coord, int K) {
        PriorityQueue<Point> loc = new PriorityQueue<>();
        for (int i = 0; i < coord.length; i++) {
            int x = coord[i][0];
            int y = coord[i][1];
            loc.add(new Point(x, y, x*x + y*y, i));
        }

        int size = loc.size();
        for (int i = 0; i < K && i < size; i++) {
            System.out.print("C" + loc.remove().idx + " ");
        }
    }

    static int costToConnectNRopes (int[] ropes) {
        PriorityQueue<Integer> ropeLen = new PriorityQueue<>();
        for (int i = 0; i < ropes.length; i++) {
            ropeLen.add(ropes[i]);
        }

        int totalCost = 0;
        while (ropeLen.size() > 1) {
            int firstMin = ropeLen.remove();
            int secMin = ropeLen.remove();
            totalCost += firstMin + secMin;
            ropeLen.add(firstMin + secMin);
        }

        return totalCost;
    }

    static class Soldiers {
        int soldiers;
        int idx;

        Soldiers (int number, int idx) {
            this.soldiers = number;
            this.idx = idx;
        }
    }
    static void K_weakestRows (int[][] row, int K) {
        PriorityQueue<Soldiers> weakestFirst = new PriorityQueue<>((a, b) -> (a.soldiers - b.soldiers != 0) ? a.soldiers - b.soldiers : a.idx - b.idx);
        for (int i = 0; i < row.length; i++) {
            int start = 0, end = row[i].length-1;
            int count = 0;
            while (start <= end) {
                int mid = start + (end-start) / 2;

                if (row[i][mid] == 0) {
                    end = mid - 1;
                }
                else {
                    count = mid;
                    start = mid + 1;
                }
            }
            weakestFirst.add(new Soldiers(count, i));
        }

        int size = weakestFirst.size();
        for (int i = 0; i < K && i < size; i++) {
            System.out.println("Row " + weakestFirst.remove().idx + "   ");
        }
    }

    static class Pair implements Comparable<Pair>{
        int val;
        int idx;
        Pair (int val, int id) {
            this.val = val;
            this.idx = id;
        }

        @Override
        public int compareTo (Pair p2) {
            return p2.val - this.val;
        }
    }
    static int[] maxOfAllSubArrays_ofSizeK (int[] arr, int K) { // TC -> O(n*logK)
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] res = new int[arr.length - K + 1];
        
        int i = 0;
        for (; i < K; i++) {
            pq.add(new Pair(arr[i], i));
        }

        res[0] = pq.peek().val;

        for (; i < arr.length; i++) {
            while (pq.size() > 0 && pq.peek().idx <= i-K) {
                pq.remove();
            }

            pq.add(new Pair(arr[i], i));
            res[i-K+1] = pq.peek().val;
        }

        return res;
    }

    public static void main (String[] args) {
        // int N = 5;
        // int arr[] = {2,3};
        // for (int i = 0; i < r.length;i++) {
        //     System.out.print(r[i]+"  ");
        // }

        //         2
        //     4       5
        //   7   10
    }
}
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class _27_HeapPQ_Questions {
    static Integer[] KthLargestAtAnyPoint (int[] integers, int K) {
        if (K < 1 || K > integers.length) {
            return new Integer[integers.length];
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Integer[] res = new Integer[integers.length];

        int i = 0;
        for (; i < K; i++) {
            pq.add(integers[i]);
        }

        res[i-1] = pq.peek();
        for (; i < integers.length; i++) {
            if (integers[i] > pq.peek()) {
                pq.poll();
                pq.add(integers[i]);
            }
            res[i] = pq.peek();
        }

        return res;
    }

    static int minTimeToFillN_Slots (int[] arr, int N) {
        if (arr.length > N) {
            System.out.println("Wrong Input!");
            return -1;
        }

        int K = arr.length;
        Queue<Integer> q = new LinkedList<>();
        boolean[] isFilled = new boolean[N + 1];

        for (int i = 0; i < K; i++) {
            if (arr[i] > N) {
                System.out.println("Wrong Input!");
                return -1;
            }
            isFilled[arr[i]] = true;
            q.add(arr[i]);
        }

        int time = 0;
        
        while (q.size() > 0) {

            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                if (curr - 1 >= 1 && !isFilled[curr - 1]) {
                    isFilled[curr - 1] = true;
                    q.add(curr - 1);
                }
                if (curr + 1 <= N && !isFilled[curr + 1]) {
                    isFilled[curr + 1] = true;
                    q.add(curr + 1);
                }
            }

            time++;
        }

        return time - 1;
    }

    static int minOperationsToHalfArraySum (int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int sum = 0;
        
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
            sum += arr[i];
        }

        float halfSum = (float)sum / 2;
        int count = 0;
        while (sum > halfSum) {
            int ele = pq.poll() / 2;
            pq.add(ele);
            sum -= ele;
            count++;
        }

        return count;
    }

    static class Node {
        int val;
        Node next;

        Node (int val) {
            this.val = val;
            this.next = null;
        }
    }
    static class NodeComparator implements Comparator<Node> {
        public int compare (Node k1, Node k2) {
            return k1.val - k2.val;
        }
    }
    static Node mergeK_sortedLinkedLists (Node[] arr, int K) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
        Node head = new Node(0);
        Node last = head;
        for (int i = 0; i < K; i++) {
            if (arr[i] != null) {
                pq.add(arr[i]);
            }
        }
        if (pq.isEmpty()) {
            return null;
        }
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            last.next = curr;
            last = last.next;
            if (curr.next != null) {
                pq.add(curr.next);
            }
        }
        
        return head.next;
    }
    private static void printLL (Node head) {
        while (head != null) {
            System.out.println(head.val + "  ");
            head = head.next;
        }
    }

    public static void main (String[] args) {
        int N = 3;
        Node[] a = new Node[N];
        Node head1 = new Node(1);
        a[0] = head1;
        head1.next = new Node(3);
        head1.next.next = new Node(5);
        head1.next.next.next = new Node(7);
        Node head2 = new Node(2);
        a[1] = head2;
        head2.next = new Node(4);
        head2.next.next = new Node(6);
        head2.next.next.next = new Node(8);
        Node head3 = new Node(0);
        a[2] = head3;
        head3.next = new Node(9);
        head3.next.next = new Node(10);
        head3.next.next.next = new Node(11);
        Node res = mergeK_sortedLinkedLists(a, N);
        if (res != null) printLL(res);
        System.out.println();
    }
}
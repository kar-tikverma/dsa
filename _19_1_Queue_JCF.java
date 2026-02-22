import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class _19_1_Queue_JCF {

    static void printQueue(Queue<Integer> q) {
        while (!q.isEmpty()) {
            System.out.print(q.remove() + " ");
        }
    }

    static void firstNonRepeating(String str) {
        Queue<Character> q = new LinkedList<>();
        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            freq[ch - 'a']++;
            q.add(ch);
            while (!q.isEmpty() && freq[q.peek() - 'a'] > 1) {
                q.remove();
            }
            if (!q.isEmpty()) {
                System.out.print(q.peek() + " ");
            } else {
                System.out.print(-1 + " ");
            }
        }
    }

    static Queue<Integer> interleaveQueue(Queue<Integer> q2) {
        Queue<Integer> q1 = new LinkedList<>();
        int size = q2.size();
        for (int i = 0; i < size / 2; i++) {
            q1.add(q2.remove());
        }
        Queue<Integer> q = new LinkedList<>();
        while (!q1.isEmpty()) {
            q.add(q1.remove());
            q.add(q2.remove());
        }
        return q;
    }

    static void reverse(Queue<Integer> q) {
        Stack<Integer> stk = new Stack<>();
        while (!q.isEmpty()) {
            stk.push(q.remove());
        }
        while (!stk.isEmpty()) {
            q.add(stk.pop());
        }
    }

    static void generateBinaryNumbers(int n) {
        Queue<String> q = new LinkedList<String>();
        q.add("1");
        while (n-- > 0) {
            String s1 = q.peek();
            q.remove();
            System.out.println(s1);
            String s2 = s1;
            q.add(s1 + "0");
            q.add(s2 + "1");
        }
    }

    static int connectRopes_withMinCost(int[] arr, int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = 0; i < n; i++) {
            pq.add(arr[i]);
        }
        int res = 0;
        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            res += first + second;
            pq.add(first + second);
        }
        return res;
    }

    static Queue<Integer> reverseFirstKElements(Queue<Integer> q, int K) {
        Stack<Integer> stk = new Stack<>();
        int size = q.size();
        for (int i = 0; i < K && i < size; i++) {
            stk.push(q.remove());
        }
        Queue<Integer> q1 = new LinkedList<>();
        while (!stk.isEmpty()) {
            q1.add(stk.pop());
        }
        while (!q.isEmpty()) {
            q1.add(q.remove());
        }
        return q1;
    }

    static void maximumOfSubArrays_Q5(int[] arr, int K) {
        Deque<Integer> dq = new LinkedList<Integer>();
        int N = arr.length;
        int i;
        for (i = 0; i < K; ++i) {
            while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()]) {
                dq.removeLast();
            }
            dq.addLast(i);
        }
        for (; i < N; ++i) {
            System.out.print(arr[dq.peek()] + " ");
            if ((!dq.isEmpty()) && dq.peek() <= i - K) {
                dq.removeFirst();
            }
            while ((!dq.isEmpty()) && arr[i] >= arr[dq.peekLast()]) {
                dq.removeLast();
            }
            dq.addLast(i);
        }
        System.out.println(arr[dq.peek()]);
    }

    static void minimumOfSubArrays_Q5(int[] arr, int K) {
        Deque<Integer> dq = new LinkedList<Integer>();
        int N = arr.length;
        int i;
        for (i = 0; i < K; ++i) {
            while (!dq.isEmpty() && arr[i] <= arr[dq.peekLast()]) {
                dq.removeLast();
            }
            dq.addLast(i);
        }
        for (; i < N; ++i) {
            System.out.print(arr[dq.peek()] + " ");
            if ((!dq.isEmpty()) && dq.peek() <= i - K) {
                dq.removeFirst();
            }
            while ((!dq.isEmpty()) && arr[i] <= arr[dq.peekLast()]) {
                dq.removeLast();
            }
            dq.addLast(i);
        }
        System.out.println(arr[dq.peek()]);
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 1, 4, 5, 2, 3, 6 };
        minimumOfSubArrays_Q5(nums, 3);
    }
}
import java.util.Deque;
import java.util.LinkedList;

public class _19_2_StacknQueue_usingDeque {
    static class Stacks {
        Deque<Integer> dq = new LinkedList<>();

        boolean isEmpty() {
            return dq.isEmpty();
        }

        void push(int data) {
            dq.addLast(data);
        }

        int pop() {
            return dq.removeLast();
        }

        int peek() {
            return dq.getLast();
        }
    }

    static class Queues {
        Deque<Integer> dq = new LinkedList<>();

        boolean isEmpty() {
            return dq.isEmpty();
        }

        void add(int data) {
            dq.addLast(data);
        }

        int remove() {
            return dq.removeFirst();
        }

        int peek() {
            return dq.getFirst();
        }
    }
}